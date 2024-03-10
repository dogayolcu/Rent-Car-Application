/**
 * @author Doğa Yolcu ID:22098727  email:dogayolcuu@gmail.com
 * InMemoryCarDB.java
 * Sisteme yüklenecek olan arabalardan bir araba listesi oluşturulmuştur.
 *
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class InMemoryCarDB {

	public ArrayList<Car> list= new ArrayList<>();
	//Car car1=new Car("manual","diesel","sedan","Ankara","Bursa",4,true,"06 ABC 78");
	//Car car2=list.add(new Car("manuel","diesel","sedan","Antalya","Bursa",2,true,"06 CD 780"));;
	
	public ArrayList<Car> GetCarList()
	{
		list.add(new Car("manual","diesel","sedan","Antalya","Bursa",2,true,"06 CD 780",200,100));
		list.add(new Car("automatic","petrol","hatchback","Ankara","İstanbul",2,true,"06 CTH 070",500,250));
		list.add(new Car("manual","diesel","SUV","İzmir","Bursa",4,true,"06 CD 780",300,150));
		list.add(new Car("automatic","diesel","sedan","Ankara","Bolu",4,true,"06 KK 910",200,100));
		list.add(new Car("manual","diesel","station wagon","Muğla","Bursa",2,true,"06 BGT 930",400,200));
		list.add(new Car("automatic","diesel","pick up","Ankara","Bursa",4,true,"06 UC 629",350,175));
		list.add(new Car("manual","diesel","sedan","Antalya","Eskişehir",2,true,"06 SLA 2506",450,225));
		return list;
	}

	
}
