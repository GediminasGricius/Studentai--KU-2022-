package lt.ku.studentai.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="groups_student")
public class Group {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 64)
	private String name;
	
	@Column
	private Integer year;
	
	@Column
	private String fileName;
	
	@OneToMany(mappedBy = "group", fetch = FetchType.EAGER)
	private List<Student> students;
	
	public Group() {
		
	}

	public Group(String name, Integer year) {
		this.name = name;
		this.year = year;
	}

	public Group(String name, Integer year, String fileName) {
		super();
		this.name = name;
		this.year = year;
		this.fileName = fileName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + ", year=" + year + "]";
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	

}
