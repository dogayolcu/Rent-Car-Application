/**
 * @author Doğa Yolcu ID:22098727  email:dogayolcuu@gmail.com
 * Car.java
 * GUI tasarımı
 *
 */
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.*; 
import java.awt.event.MouseAdapter;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.awt.event.*; // ActionListener
import com.toedter.calendar.JDateChooser;
import java.time.*;
import java.util.Calendar;
import java.time.temporal.*;


public class RentCar extends JFrame implements ActionListener{

	private Reservation reservation;

	private JTable cars;
	private JScrollPane scroll;
	private InMemoryCarDB memory;
	private JButton confirm,reservate; 
	private JTextField clientID,payment2;
	private JCheckBox ch1,ch2,ch3,ch4,ch5;
	private JDateChooser startDate,endDate;
	private JLabel payment,extras,start,end,space,client,space2,space3;
	private DateFormat sDate,eDate;
	private String sDate1,eDate1;
	private long extrasTotal=0;
	private long money,moneyWeekend;
	private FileOperations file;
	private JTextArea bill;
	private String gear, fuel_type, vehicle_group, pick_up_location,return_location, door,available, number_plate,rent,rentOfWeekEnd,ID,totalMoney;
	
	
	public RentCar ()
	{
		
		memory=new InMemoryCarDB();
		
		ArrayList<Car> carList = memory.GetCarList();
        
		String[] columnNames= {"Gear","Fuel Type","Vehicle Group","Pick up Location","Return Location","Number of Door","Availability","Plate","Rent/Mid-Week(Day)","Rent/Weekend(Day)"}; //Tabloya konacak olan kategorilerin başlıkları
		
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
		
        for (Car car : carList) 
        {
        	String gear = car.getGear();
        	String fuel_type = car.getFuel_type();
        	String vehicle_group = car.getVehicle_group();
        	String pick_up_location = car.getPick_up_location();
        	String return_location = car.getReturn_location();
        	int door = car.getDoor();
        	boolean available = car.isAvailable();
        	String number_plate = car.getNumber_plate();
        	int rent = car.getRent();
        	int rentOfWeekEnd=car.getRentOfWeekEnd();
        	
        	Object[] data = {gear, fuel_type, vehicle_group, pick_up_location, return_location, door, 
        			available, number_plate, rent,rentOfWeekEnd};

        	tableModel.addRow(data); //Car Attribute'lerin tabloya yerleştirilmesi
        }
		
		ch1=new JCheckBox("Child Seats           ");
		ch2=new JCheckBox("Roof Rack             ");
		ch3=new JCheckBox("Trailer           ");
		ch4=new JCheckBox("Snow Chains           ");
		ch5=new JCheckBox("Navigation           ");
		
		cars=new JTable(tableModel) 
				{
			 public boolean isCellEditable(int row, int column)// Tablodaki verilerin kullanıcı tarafından değiştirilmesinin önlenmesi
			 {
			     return false;
			 }
			};
		
		scroll=new JScrollPane(cars);
		scroll.setPreferredSize(new Dimension(850,200));
		cars.setBounds(0,100,40,40);
		confirm=new JButton("Confirm");
		confirm.setPreferredSize(new Dimension(150,20));
		
		reservate=new JButton("Reservation");
		reservate.setPreferredSize(new Dimension(150,20));
	
		extras=new JLabel("Extras: ");
		payment=new JLabel("                      Payment:   ");
		payment2=new JTextField (10);
		payment2.setEditable(false);
		start=new JLabel("    Start Date: ");
		end=new JLabel("       End Date:   ");
		space=new JLabel("          ");
		space2=new JLabel("                                                                                                                                                                                                                                     ");
		client=new JLabel("Client ID:  ");
		space3=new JLabel("          ");
		
		clientID=new JTextField(10);
	
		sDate=new SimpleDateFormat("dd-MM-yyyy");
		eDate=new SimpleDateFormat("dd-MM-yyyy");
		
		startDate=new JDateChooser();
		startDate.setPreferredSize(new Dimension(150,20));
		endDate=new JDateChooser();
		endDate.setPreferredSize(new Dimension(150,20));
		
		bill=new JTextArea();
		bill.setPreferredSize(new Dimension(300,300));
		bill.setEditable(false);
		
		
		Container c = getContentPane();
		c.setLayout(new FlowLayout());
		c.add(scroll);
		c.add(extras);
		c.add(ch1);
		c.add(ch2);
		c.add(ch3);
		c.add(ch4);
		c.add(ch5);
		
		
		c.add(client);
		c.add(clientID);
		
		c.add(start);
		c.add(startDate);
		
		c.add(end);
		c.add(endDate);
		
		
		c.add(space2);
		
		c.add(payment);
		c.add(payment2);
		c.add(space);
		c.add(confirm);
		c.add(space3);
		c.add(reservate);
		
		c.add(bill);
	
	
		ch1.addActionListener(this);
		ch2.addActionListener(this);
		ch3.addActionListener(this);
		ch4.addActionListener(this);
		ch5.addActionListener(this);
		
		
		cars.isCellEditable(2,3);
		
		cars.addMouseListener(new java.awt.event.MouseAdapter() {  //Jtable'da bir satıra tıklanıp seçildiğinde , seçilen arabanın attribute'lerinin alınması
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		    	
		        int row = cars.rowAtPoint(evt.getPoint());
		    
		        if (row >= 0 ) {
		        	gear=cars.getValueAt(row, 0).toString();
					fuel_type=cars.getValueAt(row, 1).toString();
					vehicle_group=cars.getValueAt(row, 2).toString();
					pick_up_location=cars.getValueAt(row, 3).toString();
					return_location=cars.getValueAt(row, 4).toString();
					door=cars.getValueAt(row, 5).toString();
					available=cars.getValueAt(row, 6).toString();
					number_plate=cars.getValueAt(row, 7).toString();
					rent=cars.getValueAt(row, 8).toString();
					rentOfWeekEnd=cars.getValueAt(row, 9).toString();
		        }	
		        
		    }
		});
		
		confirm.addActionListener(
				new ActionListener()
				{
	
					public void actionPerformed(ActionEvent e)
					{
						sDate1=sDate.format(startDate.getDate()); //JDateChooser'dan seçilen başlangıç tarhinin stringe dönüştürülmesi
						eDate1=eDate.format(endDate.getDate());  //JDateChooser'dan seçilen bitiş tarhinin stringe dönüştürülmesi
						
						Reservation.findDay(sDate1, eDate1); ////Seçilen başlangıç tarihi ile bitiş tarihi aralığında kaç günün hafta içi kaç günün haftasonu olduğunun kontrolü
						Reservation.find(sDate1,eDate1); //Seçilen başlangıç tarihi bitiş tarihinden önce mi değil mi kontrolü.
						
						money=Long.parseLong(rent); 
						moneyWeekend=Long.parseLong(rentOfWeekEnd);
						totalMoney=String.valueOf(Reservation.Money(money,moneyWeekend,extrasTotal)); //Ödenecek toplam paranın hesaplanması
						
						payment2.setText(totalMoney);
						ID=clientID.getText();
						bill.setText("Client ID: "+ ID+"\n"+"Rented dates: "+sDate1+" and " + eDate1 +"\n"+"Plate of Rented Car: " + number_plate+"\n"+"Payment: "+totalMoney); //Yine aynı durumda fatura kullanıcıya gösterilir.
						boolean situation=Reservation.Availability(sDate1, eDate1, number_plate); //Yeni kullanıcının seçtiği tarihlerde arabanın müsaitlik durumunun kontrolü.
						reservate.setEnabled(situation);
					
					}
				}
				);
		
		
		reservate.addActionListener(
				new ActionListener()
				{
	
					public void actionPerformed(ActionEvent e){
						file=new FileOperations();
						ID=clientID.getText();
						
						reservation=new Reservation(ID,sDate1,eDate1,gear,fuel_type,vehicle_group,pick_up_location,return_location,number_plate,totalMoney,rentOfWeekEnd); 
					    reservation.LogToFile();//Alınan bilgilere göre rezervasyon dosyaya yazdırılır
						file.ClientLog(ID); //Müşteri dosyası için numaraların gönderilmesi
						file.CarPlate(number_plate);//Araba dosyası için numaraların gönderilmesi
						
						
					}
				}
				);	
}
	
		
	public static void main(String[] args) {
		
	RentCar car=new RentCar();
		
		car.setTitle("Rent Car");
		car.setSize(850,700);
		car.setVisible(true);
		
		car.setResizable(false);
		car.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		long ex=0;
		if(ch1.isSelected())
		{
			 ex+=100;
		}
	     if(ch2.isSelected())
		{
			 ex+=200;
		}
	     
		 if(ch3.isSelected())
		{
			 ex+=300;
		}
		
		 if(ch4.isSelected())
		{
			 ex+=400;
		}

		 if(ch5.isSelected())
		{
			 ex+=500;
		}
		 
		extrasTotal=ex;
	    
	}  
	
	}






	



