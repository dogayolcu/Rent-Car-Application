/**
 * @author Doğa Yolcu ID:22098727  email:dogayolcuu@gmail.com
 * Reservation.java
 * Rezervasyon yapılması için kriterlerin uygun olup olmadığının kontrolü, yapılacak ödemenin hesaplanması ve rezervasyon dosyasının yazılması bu class'ta yapılmaktadır.
 *
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;

public class Reservation {
	
	private static long difference=0;
	private String gear;
	private String fuel_type;
	private String vehicle_group;
	private String pick_up_location;
	private String return_location;
	private String available;
	private String number_plate;
	private String rent;
	private String rentOfWeekEnd;
	private String sDate;
	private String eDate;
	private String ID;
	private static Calendar start;
	private static Calendar end;
	private static long weekDays=0;
	private static long weekEnds=0;
	private static long start1;
	private static long end1;
	
	 public  static void find(String join_date, String leave_date)   //Girilen başlangıç tarihinin bitiş tarinden küçük olmasının kontrolü
	    {   
	       
	        SimpleDateFormat obj = new SimpleDateFormat("dd-MM-yyyy");  
	        
	        try {   
	          
	            Date date1 = obj.parse(join_date);   
	            Date date2 = obj.parse(leave_date);   
	           
	            long time_difference = date2.getTime() - date1.getTime();  
	            
	            
	            long years_difference = (time_difference / (1000l*60*60*24*365));  
	            
	            long days_difference = (time_difference / (1000*60*60*24)) % 365 +(years_difference*365);
	             
	           
	            difference=days_difference;
	            if(difference<0)
	            	JOptionPane.showMessageDialog(null, "Please enter a valid date");
	     
	        }   
	        // Catch parse exception   
	        catch (ParseException excep) {   
	            excep.printStackTrace();   
	        }   
	     
	    }
	 
	 
	 public static void findDay(String startDate, String endDate)  //Girlen tarihler arasında fiyatlandırma için haftaiçi ve haftasonu olan günlerin sayısının bulunması
	 {
		weekEnds=0;
		weekDays=0;
		 try {
			 SimpleDateFormat obj = new SimpleDateFormat("dd-MM-yyyy"); 
				start=Calendar.getInstance();
				start.setTime(obj.parse(startDate));
				end=Calendar.getInstance();
				end.setTime(obj.parse(endDate));
				
			      while(!start.after(end))
			      {
			        
			        int day = start.get(Calendar.DAY_OF_WEEK);
			       
			        if ((day == Calendar.SATURDAY) || (day == Calendar.SUNDAY))
			        	weekEnds++;
			        else
			        	weekDays++;
			        
			        start.add(Calendar.DATE, 1);			      }
			     
			      
			    }
				
				
			 catch (ParseException e3) {
				// TODO Auto-generated catch block
				e3.printStackTrace();
			}
	
			
	 }
	 
	 
	 
	public static long Money(long rent,long rentOfWeekend,long extra) //Kiralama için ödenecek paranın bulunması
	 {
		 
		long toplamUcret=(weekEnds*rentOfWeekend)+(weekDays*rent)+extra*(weekDays+weekEnds);
		 
		return toplamUcret;
	 }
	
	
	
	public static boolean Availability(String join,String leave,String plate) //Arabının seçilen tarihlerdeki müsaitlik durumu
	{
		boolean durum = true;
		
		SimpleDateFormat obj = new SimpleDateFormat("dd-MM-yyyy"); 
		 try {
			 Date date1 = obj.parse(join);   
	         Date date2 = obj.parse(leave);
			 

	        end1=date2.getTime();
	        start1=date1.getTime();
	  
		} 
		 
		 catch (ParseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}  
		 
		 
		 try {
			 String path="/Users/dogayolcu/Desktop/reservation.txt";
			 String line="";
			 BufferedReader br=new BufferedReader(new FileReader(path));
			 
				
				while((line=br.readLine()) !=null)
				{
			
					String [] values=line.split(",");
					if(values[8].equals(plate)) {
						Date startDates= obj.parse(values[1]);
						Date endDates=obj.parse(values[2]);
						
						long startD=startDates.getTime();
						long endD=endDates.getTime();
						
					
						if(start1>=startD && start1<=endD)
						{
							durum=false;
							 JOptionPane.showMessageDialog(null, "SEÇİLEN TARİHLERDE ARABA MÜSAİT DEĞİLDİR"); 
							
							break;
									
						}
						else if(end1>=startD && end1<=endD)
						{
							durum=false;
							JOptionPane.showMessageDialog(null, "SEÇİLEN TARİHLERDE ARABA MÜSAİT DEĞİLDİR");
							break;
						}
						else
						{
							durum= true;
						}
					}
					
					else {
						durum=true;
					}
				}
				br.close();
				
				
			}catch(FileNotFoundException e1)
			{
				e1.printStackTrace();
			}catch(IOException e1)
			{
				e1.printStackTrace();
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		 return durum;
         
	}
	
	

	
     public Reservation(String ID,String sDate,String eDate,String gear,String fuel_type,String vehicle_group,String pick_up_location,String return_location,String number_plate,String rent,String rentOfWeekEnd) //Rezervasyon constructor'ı
     {
    	 this.gear=gear;
 		this.fuel_type=fuel_type;
 		this.vehicle_group=vehicle_group;
 		this.pick_up_location=pick_up_location;
 		this.return_location=return_location;
 		this.number_plate=number_plate;
 		this.rent=rent;
 		this.ID=ID;
 		this.sDate=sDate;
 		this.eDate=eDate;
 		this.rentOfWeekEnd=rentOfWeekEnd;
     }

	 public void LogToFile () { //Oluşturulan rezervasyonun rezervasyon dosyasına yazılması
		
		try {
			
			BufferedWriter bw = new BufferedWriter(new FileWriter("/Users/dogayolcu/Desktop/reservation.txt",true));
			bw.write(this.toString());
			bw.close();
			JOptionPane.showMessageDialog(null, "Rezervasyonunuz tamamlanmıştır.");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Logged to file.");
		
	 }
     
     public String toString()
     {
    	 return ID+","+sDate+","+eDate+","+gear +","+ fuel_type +"," + vehicle_group +","+ pick_up_location + ","+ return_location + "," + number_plate + "," + rent + "\n";
     }
     
     
     
    

}
