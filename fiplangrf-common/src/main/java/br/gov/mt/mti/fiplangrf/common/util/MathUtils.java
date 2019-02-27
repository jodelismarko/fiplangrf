package br.gov.mt.mti.fiplangrf.common.util;

import java.lang.reflect.Array;
import java.math.BigDecimal;

@SuppressWarnings({"unchecked"})
public final class MathUtils {

	public static boolean isBigger(BigDecimal bd1, BigDecimal bd2) {
		return bd1.compareTo(bd2) == 1;
	}

	public static boolean isLesser(BigDecimal bd1, BigDecimal bd2) {
		return bd1.compareTo(bd2) == -1;
	}

	public static boolean isEqual(BigDecimal bd1, BigDecimal bd2) {
		return bd1.compareTo(bd2) == 0;
	}

	public static boolean isNonPositive(BigDecimal number) {
		return number.compareTo(ZERO) <= 0;
	}

	public static boolean isNonNegative(BigDecimal number) {
		return number.compareTo(ZERO) >= 0;
	}

	public static boolean isPositive(BigDecimal number) {
		return number.compareTo(ZERO) > 0;
	}

	public static boolean isNegative(BigDecimal number) {
		return number.compareTo(ZERO) < 0;
	}

	public static boolean isNotZero(BigDecimal number) {
		return number.compareTo(ZERO) != 0;
	}

	public static boolean isZero(BigDecimal number) {
		return number.compareTo(ZERO) == 0;
	}
	
	/**
	 * Método responsável por instanciar um Array do tipo passado no parametro.
	 */
	public static <T> T[] getArray(Class<T> classe, int qtde) throws Exception
	{
		T[] retorno= (T[])Array.newInstance(classe, qtde);
		
		try
		{
			for(int i = 0; i < qtde; i++)
			{
				if(classe.getName().equals(BigDecimal.class.getName()))
					retorno[i]= (T)BigDecimal.ZERO;
				else
					retorno[i]= classe.newInstance();
			}
		}
		catch (InstantiationException e)
		{
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			e.printStackTrace();
		}
		
		return retorno;
	}

	public static final BigDecimal ZERO = BigDecimal.ZERO;
}
