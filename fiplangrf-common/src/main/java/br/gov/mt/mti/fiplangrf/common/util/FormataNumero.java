package br.gov.mt.mti.fiplangrf.common.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

/**
 * Classe utilitária para formatações diversas de um valor numérico.
 *
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class FormataNumero {
	
//	private static NumberFormat defaultNF = new DecimalFormat("#,##0.00");

	private static final NumberFormat getDefaultNF() {
		return new DecimalFormat("#,##0.00");
	}

//	private static NumberFormat defaultNP = new DecimalFormat(
//			"###,#####000.00000");

	private static final NumberFormat getDefaultNP() {
		return new DecimalFormat("###,#####000.00000");
	}

//	private static NumberFormat defaultNF4Casas = new DecimalFormat(
//	"#,###,###,###,##0.0000");
	
	private static final NumberFormat getDefaultNF4Casas() {
		return new DecimalFormat(
		"#,###,###,###,##0.0000");
	}

//	private static NumberFormat defaultN8Casas = new DecimalFormat(
//	"#,###,###,###,##0.00000000");
	
	private static final NumberFormat getDefaultN8Casas() {
		return new DecimalFormat(
		"#,###,###,###,##0.00000000");
	}

	private static final NumberFormat getDefaultN10Casas() {
		return new DecimalFormat(
		"#,###,###,###,##0.0000000000");
	}
//	private static NumberFormat defaultN6Casas = new DecimalFormat(
//	"#,###,###,###,##0.00000000");
	
	private static final NumberFormat getDefaultN6Casas() {
		return new DecimalFormat(
		"#,###,###,###,##0.000000");
	}
	/** Array(matriz 10x2)  contendo os qualificadores monetários */
	private static String Qualificadores[][] = {
			{"centavo", "centavos"},
			{"real", "reais"},
			{"mil", "mil"},
			{"milhão", "milhões"},
			{"bilhão", "bilhões"},
			{"trilhão", "trilhões"},
			{"quatrilhão", "quatrilhões"},
			{"quintilhão", "quintilhões"},
			{"sextilhão", "sextilhões"},
			{"septilhão", "septilhões"}
			};

	/** Array(matriz 3xN) contendo a descrição de unidade(dezenna, centena, ...) */
	private static String Numeros[][] = {
			{"zero", "um", "dois", "três", "quatro", "cinco", "seis", "sete", "oito", "nove", "dez",
			"onze", "doze", "treze", "quatorze", "quinze", "dezesseis", "dezessete", "dezoito", "dezenove"},
			{"vinte", "trinta", "quarenta", "cinquenta", "sessenta", "setenta", "oitenta", "noventa"},
			{"cem", "cento", "duzentos", "trezentos", "quatrocentos", "quinhentos", "seiscentos",
			"setecentos", "oitocentos", "novecentos"}
			};



	/**
	 * Retorna apenas a parte numérica de uma string.
	 */
	public static String onlyNumericDigits(String number) {
		if (number == null)
			return number;
		return number.replaceAll("\\D+", "");
	}

	/**
	 * Método que completa zeros à esquerda de um valor numérico.
	 *
	 * @param qtdPos -
	 *            Quantidade de posições desejadas para o número.
	 * @param valor -
	 *            Valor à ser formatado.
	 * @return String formatada com zeros à esquerda.
	 */
	public static String completaZerosEsquerda(int qtdPos, Integer valor) {
		return completaZerosEsquerda(qtdPos, valor.intValue());
	}

	/**
	 * Método que completa zeros à esquerda de um valor numérico.
	 *
	 * @param qtdPos -
	 *            Quantidade de posições desejadas para o número.
	 * @param valor -
	 *            Valor à ser formatado.
	 * @return String formatada com zeros à esquerda.
	 */
	public static String completaZerosEsquerda(int qtdPos, int valor) {
		return StringUtils.leftPad(String.valueOf(valor), qtdPos, "0");
	}

	/**
	 * Método que completa zeros à esquerda de um valor numérico.
	 *
	 * @param qtdPos -
	 *            Quantidade de posições desejadas para o número.
	 * @param valor -
	 *            Valor à ser formatado.
	 * @return String formatada com zeros à esquerda.
	 */
	public static String completaZerosEsquerda(int qtdPos, long valor) {
		return StringUtils.leftPad(String.valueOf(valor), qtdPos, "0");
	}

	/**
	 * Classe que formata moeda (sem símbolo de moeda).
	 *
	 * @param valor
	 *            o valor a ser formatado.
	 * @return o valor formatado.
	 * @deprecated Foi substituido pelo método formatarValorMonetario (Odilon)
	 */
	public static String formataMoeda(double valor) {
		return getDefaultNF().format(valor);
	}
	

	/**
	 * Classe que formata percentual (sem símbolo de moeda).
	 *
	 * @param valor
	 *            o valor a ser formatado.
	 * @return o valor formatado.
	 * @deprecated Foi substituido pelo método formatarPercentual (Odilon)
	 */
	public static String formataPercentual(double valor) {
		return getDefaultNP().format(valor);
	}

	/**
	 * Retorna o valor informadao com 4 casas decimais.
	 *
	 * @param valor
	 * @return
	 * @deprecated Foi substituido pelo método formatarValor4Casas (Odilon)
	 */
	public static String formataValor4Casas(double valor) {
		return getDefaultNF4Casas().format(valor);
	}
	/**
	 * Retorna o valor com 8 casas decimais.
	 *
	 * @param valor
	 * @return
	 * @deprecated Foi substituido pelo método formatarValor8Casas (Odilon)
	 */
	public static String formataValor8Casas(double valor) {
		return getDefaultN8Casas().format(valor);
	}
	/**
	 * Retorna um valor de moeda por extenso
	 * @param valor Valor double a ser convertido por extenso.
	 * @return Valor por extenso.
	 * @deprecated Foi substituido pelo método moedaExtenso (Odilon)
	 */
	public static String moedaExtenso(double valor) {		
		BigDecimal numero = new BigDecimal(valor);
		return moedaExtenso(numero);
	}

	/**
	 * Retorna um valor de moeda por extenso
	 * @param valor Valor BigDecimal a ser convertido por extenso.
	 * @return Valor por extenso.
	 */
	public static String moedaExtenso(BigDecimal valor) {
		
		if(MathUtils.isNegative(valor)){
		     valor = valor.negate();		     
		  }
		StringBuffer buf = new StringBuffer();
	    BigDecimal numero = valor;
	    ArrayList unidadeNumerica = obtemUnidadesNumericas(numero);
//		int numeroAux = ((Integer) unidadeNumerica.get(0)).intValue();
		int ct;

		for(ct = unidadeNumerica.size() - 1; ct > 0; ct--) {
			// Se ja existe texto e o atual não é zero
			if(buf.length() > 0 && ! ehGrupoZero(ct, unidadeNumerica))
				buf.append(" e ");
			buf.append(numrToString(((Integer) unidadeNumerica.get(ct)).intValue(), ct, buf.toString()));
		}

		if(((Integer) unidadeNumerica.get(0)).intValue() != 0) {
			//Se ja existe texto e o atual não é zero
			if (buf.length() > 0)
				buf.append(" e ");
			buf.append(numrToString(((Integer) unidadeNumerica.get(0)).intValue(), 0, buf.toString()));
		}

	    return buf.toString();
	}

	/**
	 * Obtem um Array com as unidades numéricas do número passado como argumento,
	 * unidade de dezenas, centenas, milhares, etc.
	 * @param  parmNumero  Um novo numero
	 * @return unidadeNumerica Objeto da classe ArrayList,
	 * 						   com a lista de unidades do número corrente
	 */
	public static ArrayList obtemUnidadesNumericas(BigDecimal parmNumero) {
		BigInteger numrAux;

		// Converte para inteiro arredondando os centavos
		numrAux = parmNumero.setScale(2, BigDecimal.ROUND_HALF_UP)
	               .multiply(BigDecimal.valueOf(100))
	               .toBigInteger();

		// Adiciona valores
		ArrayList unidadeNumerica = new ArrayList();
		if (numrAux.equals(BigInteger.ZERO)) {
			// Centavos
			unidadeNumerica.add(Integer.valueOf(0));
			// Valor
			//unidadeNumerica.add(new Integer(0));
		} else {
			// Adiciona centavos
			numrAux = adicionaResto(numrAux, 100, unidadeNumerica);

			// Adiciona grupos de 1000
			while (!numrAux.equals(BigInteger.ZERO)) {
				numrAux = adicionaResto(numrAux, 1000, unidadeNumerica);
			}
		}

		return (unidadeNumerica);
	}

	/**
	 * Divide o valor corrente em dezenas, centenas, milhares, etc.
	 * e guada essas partes no Array unidadeNumerica
	 * @param parmNumero Valor a ser dividido.
	 * @param divisor Novo divisor pelo qual o valor corrente do
	 *         atributo num será dividido.
	 */
	private static BigInteger adicionaResto(BigInteger parmNumero, int divisor, ArrayList unidadeNumerica) {
		// Encontra novoNumero[0] = parmNumero / divisor.
		// novoNumero[1] = parmNumero % divisor.
		BigInteger[] novoNumero = parmNumero.divideAndRemainder(BigInteger.valueOf(divisor));
		unidadeNumerica.add(Integer.valueOf(novoNumero[1].intValue()));
		// Retorna um novo numero, numero este sem a última parte(decimal, centena, milhar, etc)
		return (novoNumero[0]);
	}

	/**
	 * Verifica se o grupo monetário corrente é zero
	 * @param ps Valor a ser vereficado se é igual a zero.
	 * @return true se o grupo monetário corrente for zero,
	 *        false caso contrário
	 */
	private static boolean ehGrupoZero(int ps, ArrayList unidadeNumerica) {
		if (ps <= 0 || ps >= unidadeNumerica.size())
			return true;
		return ((Integer)unidadeNumerica.get(ps)).intValue() == 0;
	}

	/**
	 * Monta Texto do valor corrente por extenso
	 * @param  numero  Número a ser passado por extenso
	 * @param  escala Escala do qualificador
	 * @param  valorExtenso Valor por extenso até o momento.
	 * @return String com o texto do valor corrente por extenso
	 */
	private static String numrToString(int numero, int escala, String valorExtenso) {
		int unidade = (numero % 10);
		int dezena = (numero % 100); //* nao pode dividir por 10 pois verifica de 0..19
		int centena = (numero / 100);
		StringBuffer buf = new StringBuffer();

		if (numero != 0) {
			if (centena != 0) {
				if (dezena == 0 && centena == 1) {
					buf.append(Numeros[2][0]);
				} else {
					buf.append(Numeros[2][centena]);
				}
			}

			if ((buf.length() > 0) && (dezena != 0)) {
				buf.append(" e ");
			}
			if (dezena > 19) {
				dezena /= 10;
				buf.append(Numeros[1][dezena - 2]);
				if (unidade != 0) {
					buf.append(" e ");
					buf.append(Numeros[0][unidade]);
				}
			} else if (centena == 0 || dezena != 0) {
				buf.append(Numeros[0][dezena]);
			}

			buf.append(" ");
			if (numero == 1) {
				buf.append(Qualificadores[escala][0]);
			} else {
				buf.append(Qualificadores[escala][1]);
			}
		}

		if(escala==1 && numero==0) {
			if(!("mil".equals(valorExtenso.substring(valorExtenso.length()-3))))
				buf.append(" de ");
			else
				buf.append(" ");
			buf.append(Qualificadores[escala][1]);
		}

		return buf.toString();
	}

    /**
     * @param property
     * @return
     */
    public static boolean isFloatingPoint(String nmCampo) {
        return nmCampo!=null && (nmCampo.startsWith("vl") || nmCampo.startsWith("valor") || nmCampo.startsWith("valr") || nmCampo.startsWith("pct"));
    }

    
    /**
     * Para definir apenas duas casas decimais no BigDecimal.
     * 
     * @param valor 0.1234567879 
     * @return BigDecimal com apenas exemplo 0.01
     * 
     */
    public static BigDecimal arredondarBigDecimal(BigDecimal valor) {
		return (valor != null) ? valor.setScale(2, BigDecimal.ROUND_HALF_UP)
				: new BigDecimal(0);
	}
    
	/**
	 * Formata para Valor Monetário em Reais.
	 * 
	 * @param valor 1000.00
	 * @return valor 1.000,00
	 */
	public static String formatarValorMonetario(BigDecimal valor) {
		return getDefaultNF().format(valor);
	}
	
	
	/**
	 * Formata para Valor Monetário em Reais Recebendo uma String como parametro.
	 * 
	 * @param valor 1000.00
	 * @return valor 1.000,00
	 */
	public static String formatarStrValorMonetario(String valor) {
		BigDecimal valorBig = new BigDecimal(valor);
		return getDefaultNF().format(valorBig);
	}
	
	/**
	 * Formata para Valor Monetário em Reais retirando o sinal de negativo.
	 * 
	 * @param valor -1000.00
	 * @return valor 1.000,00
	 */
	public static String formatarValorMonetarioPositivo(BigDecimal valor) {
		return getDefaultNF().format(valor.abs());
	}
	
	public static String formatarPercentual(BigDecimal valor) {
		return getDefaultNP().format(valor);
	}
	
	public static String formatarValor4Casas(BigDecimal valor) {
		return getDefaultNF4Casas().format(valor);
	}
	
	public static String formatarValor8Casas(BigDecimal valor) {
		return getDefaultN8Casas().format(valor);
	}

	public static String formatarValor10Casas(BigDecimal valor) {
		return getDefaultN10Casas().format(valor);
	}
	
    /**
	 * Classe que formata moeda (sem símbolo de moeda), porém qdo o
	 * valor for "-0,00", será substituido por "0,00".
	 * @param valor a ser formatado.
	 * @return o valor formatado.
	 */
	public static String formataMoedaZeroPositivo(BigDecimal valor) {
		return (!getDefaultNF().format(valor).equals("-0,00")) ? getDefaultNF().format(valor) : "0,00";
	}

	
    /**
	 * Classe que formata moeda (sem símbolo de moeda), porém qdo o
	 * valor for "-0,00", será substituido por "0,00".
	 * @param valor a ser formatado.
	 * @return o valor formatado.
	 * @deprecated Foi substituido pelo método formatarValorMonetarioPositivo (Odilon)
	 */
	public static String formataMoedaZeroPositivo(double valor) {
		return (!getDefaultNF().format(valor).equals("-0,00")) ? getDefaultNF().format(valor) : "0,00";
	}
	
	/**
	 * Metodo responsável por validar se o valor passado por parametro é positivo ou negativo
	 * Caso positivo retorna mascarado do tipo moeda
	 * Caso negativo retorna o valor entre parenteses sem sinal negativo
	 */
	public  static String formataMoedaValorNegativoComParenteses(BigDecimal valor) {
		String valorString = null;
		if (MathUtils.isNegative(valor)) {
			valor = valor.negate();
			valorString = "(" + formataMoedaZeroPositivo(valor) + ")";
		} else {
			valorString = formataMoedaZeroPositivo(valor);
		}
		return valorString;
	}
	/**
	 * Formata para Valor Monetário com 6 casas decimais.
	 * 
	 * @param valor 1000.00
	 * @return valor 1.000,000000
	 * 
	 */
	
	
	public static String formatarValor6Casas(BigDecimal valor) {
		return getDefaultN6Casas().format(valor);
	}
	/**
	 * Converte uma string formatada para um BigDecimal
	 * 
	 * @param (String) 1.000,05 
	 * @return (BigDecimal) 1000.05 
	 * 
	 */
	public static BigDecimal string2BigDecimal(String val){
		return new BigDecimal(val.replace(".", "").replace(",", "."));
	}
	
	
}
