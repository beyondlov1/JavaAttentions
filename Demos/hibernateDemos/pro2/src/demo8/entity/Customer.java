package demo8.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Customer_8_3")
public class Customer {
	@Id
	@GeneratedValue(generator = "myUUID")
	@GenericGenerator(strategy = "uuid", name = "myUUID")
	private String id;

	@Column(name = "ADDRESS")
	private String address;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
