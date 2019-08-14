package cn.deepcoding.service;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;
import org.apache.ibatis.annotations.Param;
import cn.deepcoding.model.Classroom;
import cn.deepcoding.model.Major;
import cn.deepcoding.model.ProxyTeacherAmount;
import cn.deepcoding.model.Student;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.model.StudyTime;    
import cn.deepcoding.model.Tuition;

public interface StudentService {

	Student getById(Integer id);

	public void save(Student student, String[] cname, String[] ctel, String[] crelation);

	public Student getByName(String name);

	public List<Student> getByClassId(int id);

	List<Student> getByClassId(Page page, Integer classId);

	List<Student> getByClassIdMaId(Integer classId, Integer majorId, String studentName);

	public void update(Student student);

	// 妯＄硦鏌ヨ濮撳悕
	public Object listAll(Page page, String stuName, String stuIdCard);

	// 鐛插彇鎵�鏈夊鐢熶俊鎭�
	public List<Student> getStudent(Student Student);

	public List<Student> getClassStudents(Integer id);

	public void updateTestClassId(Integer testClassId, Integer[] studentIds);

	// 鑾峰彇鎵�鏈夌殑妯℃嫙鐝骇Id
	List<Integer> listTestClassroomId();

	// 閫氳繃妯℃嫙鐝骇Id鑾峰彇瀛︾敓
	Object getByTestClassId(Page page, Integer testClassId);

	// 绉婚櫎妯℃嫙鐝骇Id
	void deleteTestClassId(Integer id);

	void updateClassId(Integer testClassId, Integer[] studentIds);

	// 閫氳繃濮撳悕鐢佃瘽鏌ュ埌dingId 鑰冨嫟闇�瑕侊紝鍕垮姩
	String getDingId(String name, String tel);

	// 閫氳繃瀛︾敓濮撳悕浠ュ強鐢佃瘽娣诲姞瀛︾敓Dingid淇℃伅
	void updateDingIdByNameTel(String userId, String name, String tel);

	// 鏌ヨ娌℃湁DingId鐨勫鐢�
	List<Student> getNameTel();

	// 鑾峰彇瀛︾敓淇℃伅
	public List<Student> getName(Integer id);

	List<Student> getTelByName(String name);

	public List<Student> select(String name, String sex, String idcard);

	// 閫氳繃瀛︾敓濮撳悕妯＄硦鏌ヨ鍜岀彮绾d鏌ヨ
	PageData getSomeStudents(Student student, String examName, Page page);

	// 鐜嬫檽瀹�
	public Object getAll(Page page, String stuName, String idCard);

	// 鍒ゆ柇韬唤璇佸彿鏄惁瀛樺湪
	Boolean idcardisNo(String idCard);

	// 鍒犻櫎瀛︾敓
	String deleteStudent(Student student);

	List<Student> export();//瀵煎嚭excel

	Student export1(Integer id);//璇︽儏瀵煎嚭excel
	//查询所有 //下拉框
	List<Student> findAll();
	//通过名字查找电话
	List<Student> findStuByName(Student student);

	
}
