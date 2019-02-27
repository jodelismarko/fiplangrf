package br.gov.mt.mti.fiplangrf.web.bean.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.annotation.PreDestroy;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.context.spi.AlterableContext;
import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.CDI;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.CroppedImage;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;

import br.gov.mt.cepromat.ceprofw.core.service.exception.BusinessException;
import br.gov.mt.mti.fiplangrf.common.security.user.UsuarioLogado;

@Named("imageUploadBean")
@SessionScoped
public class ImageUploadBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5346436320795224271L;
	
	private CroppedImage croppedImage;
	
	private UploadedFile fileUploaded;
	
	private byte[] bytes;
	
	private byte[] bytesUploaded;
	
	private String imageTempPath;
	
	private String imageName;
	
	private boolean readyToCrop;
	
	@Inject
	private UsuarioLogado usuarioLogado;
	
	public static synchronized ImageUploadBean get(){
		BeanManager beanManager = CDI.current().getBeanManager();
		AlterableContext ctxSession = (AlterableContext)beanManager.getContext(SessionScoped.class);
		for(Bean<?> bean : beanManager.getBeans(ImageUploadBean.class)){
			ImageUploadBean instance = (ImageUploadBean)ctxSession.get(bean);
			if(instance != null){
				return instance;
			}
		}		
		return null;
	}
	
	public static synchronized void selfDestruction(){
		BeanManager beanManager = CDI.current().getBeanManager();
		AlterableContext ctxSession = (AlterableContext)beanManager.getContext(SessionScoped.class);
		for(Bean<?> bean : beanManager.getBeans(ImageUploadBean.class)){
			ImageUploadBean instance = (ImageUploadBean)ctxSession.get(bean);
			if(instance != null){
				ctxSession.destroy(bean);
			}
		}
	}
	
	public String toSHA1(byte[] convertme) {
	    MessageDigest md = null;
	    try {
	        md = MessageDigest.getInstance("SHA-1");
	    }
	    catch(NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    } 
	    return new String(md.digest(convertme));
	}
	
	private String getNewImageName(String imageName) {
		int lastPeriod = imageName.lastIndexOf(".");
		
		String tmpImageName = new String(Base64.getEncoder().encode(				
				toSHA1(imageName.getBytes()).getBytes()
				));
		tmpImageName = tmpImageName.replaceAll("/[^A-Za-z0-9 ]/", "")
					 .replace("\\", "")
					 .replace("/","")
					 .replace("=", "_");
		if(lastPeriod > -1) {
			tmpImageName += imageName.substring(lastPeriod);
		}
		return tmpImageName;
	}
	
	private String getTempFolderName(){		
		String folder = new String(Base64.getEncoder().encode(				
				toSHA1(this.usuarioLogado.getCpf().getBytes()).getBytes()
				));
		return folder.replaceAll("/[^A-Za-z0-9 ]/", "").replace("\\", "").replace("/","");
	}
	
	private String _getImageWebPath(){
		
		return "/resources/temp/images/" + getTempFolderName() + "/";
	}
	
	private String _getImageFileTempPath() throws BusinessException {
		return _getRealPath() + 
			   File.separator + "resources" +
			   File.separator + "temp" +
			   File.separator + "images" + 
			   File.separator + getTempFolderName() +
			   File.separator;
	}

	public void clear(){
		
		setFileUploaded(null);
		setBytes(null);
		setBytesUploaded(null);
		setCroppedImage(null);
		this.imageName = null;
		this.imageTempPath = null;
		
		try {
			File file = new File(_getImageFileTempPath());
			
			if(file.exists() && file.isDirectory()){
//				FileUtils.cleanDirectory(file);
//				FileUtils.forceDeleteOnExit(file);
			}
		} catch(BusinessException ex) {
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void clearUpload() {
		byte[] temp = bytes;
		clear();
		bytes = temp;
	}

	public ImageUploadBean(){
		super();
	}
	
	public UploadedFile getFileUploaded() {
		return fileUploaded;
	}

	public void setFileUploaded(UploadedFile fileUploaded) {
		this.fileUploaded = fileUploaded;
	}
	
	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public byte[] getBytesUploaded() {
		return bytesUploaded;
	}

	public void setBytesUploaded(byte[] bytesUploaded) {
		this.bytesUploaded = bytesUploaded;
	}

	public CroppedImage getCroppedImage() {
		return croppedImage;
	}

	public void setCroppedImage(CroppedImage croppedImage) {
		this.croppedImage = croppedImage;
	}

	public String getImageTempPath() {
		return imageTempPath;
	}

	public boolean isReadyToCrop() {
		return readyToCrop;
	}

	private void setReadyToCrop(boolean readyToCrop) {
		this.readyToCrop = readyToCrop;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	private String _getRealPath() throws BusinessException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		if(facesContext == null) {
			// usuario nao logado
			throw new BusinessException();
		}
        ServletContext context = (ServletContext) facesContext.getExternalContext().getContext();

        String realPath = context.getRealPath("/");
        
        return realPath;
	}
	
	public String encodeImageName(String value) throws Exception {
		return getNewImageName(value);
	}
	
	public void handleUpload(FileUploadEvent event) {
		clearUpload();
	    try {
	    	setReadyToCrop(true);
	    	
	    	setBytesUploaded(new byte[event.getFile().getInputstream().available()]);
	    	event.getFile().getInputstream().read(getBytesUploaded());
	        
	        createTempFile(event.getFile().getFileName());

	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    } 
	}

	public void createTempFile(String filename) throws Exception {
		FileOutputStream fos = null;
		try{
			String tempPath = _getImageFileTempPath();
	
			File file = new File(tempPath);
			
			//Cria a pasta para salvar o arquivo de imagem (caso não exista)
			if(!file.exists()){
				file.mkdirs();
			}
			
			String caminho = tempPath + encodeImageName(filename);
			
			this.imageTempPath = _getImageWebPath() + encodeImageName(filename);
			this.imageName = encodeImageName(filename);
			
			fos = new FileOutputStream(caminho);
			fos.write(getBytesUploaded());
			fos.flush();
		}finally {
	    	try {fos.close();} catch(Exception e){}
	    }
	}   
	
	public StreamedContent loadImageOnView() throws IOException {
		byte[] image = null;
		try {
			if(getFileUploaded() != null){
				getFileUploaded().getInputstream();
				image = new byte[getFileUploaded().getInputstream().available()];
				getFileUploaded().getInputstream().read(image);
				setBytes(image);
			}
		} catch (Exception e) {			
		} 
		if(getBytes() != null){
			return new DefaultStreamedContent(new ByteArrayInputStream(getBytes()), "image/png");
		} else {
			return null;
		}
	}
	
	public void cortar(ActionEvent event) {
        if(croppedImage != null) {
            try {
                setBytes(croppedImage.getBytes());
            } catch (Exception e) {
                return;
            }
        } else {
            setBytes(bytesUploaded);
        }
        setReadyToCrop(false);
        setCroppedImage(null);
        removeImageFromTemp();
        this.imageTempPath = null;
    }
	
	public void cancelar(ActionEvent event) {
//        if(croppedImage == null) {
//            return;
//        }
//		setBytes(null);
		setBytesUploaded(null);
		setReadyToCrop(false);
		setCroppedImage(null);
        removeImageFromTemp();
        this.imageTempPath = null;
	}
	
	private void removeImageFromTemp(){
		try {
			String image = _getImageFileTempPath() + imageName;
			File file = new File(image);
			if(file.exists()){
				file.delete();
			}
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@PreDestroy
	private void destroy(){
		this.clear();
	}

}
