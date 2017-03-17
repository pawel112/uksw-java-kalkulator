package kalkulator;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;

/** Klasa kalkulator
*
* @author Paweł Roszatycki
* @version 16.03.2017
*/
public class kalkulator {
	
	/** Funkcja oblicza wynik działania liczb oraz operatorów na podstawie wejściowego Stringa.
	 * 
	 * 
	 * @param wejscie			Wejściowy String.
	 */
	public static void oblicz (String wejscie)
	{
		if (StringUtils.isNumeric(wejscie.substring(0, 1)) == true)
		{
			System.out.println("Pierwszy znak to cyfra.");	
		}
		else
		{
			System.out.println("Pierwszy znak nie jest cyfrą.");	
		}
		
		String[] operatory = wejscie.split("[0-9]{1,}");
		String[] liczby = wejscie.split("[+-//*/]");
		double wynik = Double.parseDouble(liczby[0]);
		int licznik = 0;
		
		for (int i=1; i<liczby.length; i++)
		{
			if (liczby[i].trim().isEmpty() == false)
			{
				for (int j=0; j<1; )
				{
					if (operatory[licznik].trim().isEmpty() == false)
					{
						switch (operatory[licznik].trim())
						{
						case "+":
							wynik += Double.parseDouble(liczby[i]);
							break;
						case "-":
							wynik -= Double.parseDouble(liczby[i]);
							break;
						case "*":
							wynik *= Double.parseDouble(liczby[i]);
							break;
						case "/":
							wynik /= Double.parseDouble(liczby[i]);
							break;
						}
						j++;
						licznik++;
					}
					else
					{
						licznik++;
					}
				}
				
			}
		}
		System.out.println(wejscie+" = "+wynik);
	}
	
	/** Funkcja prosi użytkownika o podanie obliczeń w konsoli, podanie nazwy pliku do wykonania obliczeń lub zakończenie programu.
	 * 
	 * 
	 * @param args 			Parametry uruchomienia programu.
	 */
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		for (int i=0; i<1;)
		{
			System.out.println("Menu:\n1. Obliczenia z konsoli\n2. Obliczenia z pliku\n3. Koniec programu");
			int opcja = keyboard.nextInt();
			switch (opcja)
			{
			case 1:
				System.out.println("Proszę podać obliczenia:");
				String tekst = keyboard.next();
				
				if (tekst.equalsIgnoreCase("koniec"))
				{
					System.out.println("Koniec programu.");
					i++;
					break;
				}
				else
				{
					oblicz (tekst);
				}
				break;
			case 2:
				System.out.println("Proszę podać nazwę pliku:");
				String tekst2 = keyboard.next();
				BufferedReader plik2 = null;
				try
				{            
		            plik2 = new BufferedReader(new FileReader(tekst2));
		            String l = plik2.readLine();
		            while (l != null)
		            {
		                System.out.println(l);
		                oblicz (l);
		                l = plik2.readLine();
		            }
		            plik2.close();
		        }
				catch (IOException e)
				{
					System.out.println("Wystąpił błąd.");
		        }
				break;
			case 3:
				System.out.println("Koniec programu.");
				i++;
				break;
			}
		}
		keyboard.close();
	}
	
	
}