/**
 * @author Doğa Yolcu ID:22098727  email:dogayolcuu@gmail.com
 * FileOperations .java
 * Programda istenen rezervasyon dosyasından okuma, müşteri ve araba dosyalarına yazma işlemleri bu classta yapılmıştır.
 *
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileOperations {
	private String path="/Users/dogayolcu/Desktop/reservation.txt";
	private int totalMoney = 0;
	private int idCount = 0;
	private int rentCount = 0;
	private String line="";
	private String startLocation;
	private String endLocation;
	private String rentalDate;
	private String returnDate;
	
	
	public void ClientLog(String id)//Rezervasyon dosyasından müşteri numaralarını okuyarak son giren kullanıcıyla karşılaştırır,eğer kullanıcının öncesinde bir kaydı varsa yapılan toplam ödemeyi ve kiralama sayısını bulur.
									//Bulunan bilgileri müşteri dosyasına yazar.
	{
		System.out.println("Logged to file.");
		try {
			BufferedReader br=new BufferedReader(new FileReader(path));
			BufferedWriter bw = new BufferedWriter(new FileWriter("/Users/dogayolcu/Desktop/client.txt",true));
			String [] values;
			while((line=br.readLine()) !=null)
			{
				values=line.split(",");
				
				if(values[0].equals(id)) {
					totalMoney += Integer.parseInt(values[9]);
					idCount++;
					startLocation=values[6];
					endLocation=values[7];
				}
			}
			
			bw.write("ID number:"+id+ "," +"Pick-Up Location:"+startLocation+","+"Return Location:" +endLocation+","+"Total Money:"+ totalMoney + "," + "Reservasyon sayısı:" + idCount+"\n");
			br.close();
			bw.close();

		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}catch(IOException e)
		{
			e.printStackTrace();
		}

		
	}
	
	public void CarPlate(String plate)//Rezervasyon dosyasından araba plakalarını okuyarak son kiralanan arabanın plakasıyla karşılaştırır,eğer arabanın öncesinde bir kaydı varsa toplam kiralanma sayısını ve kiralanma tarihlerini bulur.
									  //Bulunan bilgileri müşteri dosyasına yazar.
	{
		try {
			BufferedReader br=new BufferedReader(new FileReader(path));
			BufferedWriter bw = new BufferedWriter(new FileWriter("/Users/dogayolcu/Desktop/car_plate.txt",true));
			
			String [] values;
			while((line=br.readLine()) !=null)
			{
				values=line.split(",");
				
				if(values[8].equals(plate)) {
					
					rentCount++;
					rentalDate=values[1];
					returnDate=values[2];
				}
			}
			bw.write("Car Plate:"+plate+ "," +" Rental Date:"+ rentalDate + "," +" Return Date:"+ returnDate + "  Reservasyon sayısı:" + rentCount+"\n");
			br.close();
			bw.close();
			
		}catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
			
	}

}
