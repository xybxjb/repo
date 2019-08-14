package cn.deepcoding.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.deepcoding.model.Course;
import cn.deepcoding.model.Exam;
import cn.deepcoding.model.Fee;
import cn.deepcoding.model.ProxyTeacherAmount;
import cn.deepcoding.model.Score;
import cn.deepcoding.model.Student;
import org.springframework.web.multipart.MultipartFile;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import cn.deepcoding.page.Page;
import cn.deepcoding.page.PageData;
import cn.deepcoding.service.CourseService;
import cn.deepcoding.service.EmergencyPersonService;
import cn.deepcoding.service.ExamService;
import cn.deepcoding.service.FeeService;
import cn.deepcoding.service.ScoreService;
import cn.deepcoding.service.StudentService;

@Controller
@RequestMapping("student")
public class StudentController {

	@Autowired
	private StudentService studentService;
	@Autowired
	private FeeService feeService;
	@Autowired
	private ExamService examService;
	@Autowired
	private ScoreService scoreService;
	@Autowired
	private CourseService courseService;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private EmergencyPersonService emergencyPersonService;

	@RequiresPermissions("student:index")
	@RequestMapping("/index")
	public String index() {

		return "student/student";
	}

	@RequestMapping("/save")
	public String save(Student student, @RequestParam("cname") String[] cname, @RequestParam("ctel") String[] ctel,
			@RequestParam("crelation") String[] crelation) {
		studentService.save(student, cname, ctel, crelation);
		return "student/student";
	}

	@RequestMapping("/savePic")
	@ResponseBody
	public String savePic(@RequestParam(value = "file") MultipartFile file) {
		if (!file.isEmpty()) {
			try {
				// �ļ�����·��
				String path = request.getSession().getServletContext().getRealPath("");
				System.err.println(path);
				path = path.replaceAll("proxy_manage", "");
				System.err.println(path);
				path = path.substring(0, path.length() - 1);
				System.out.println(path);
				File newFile = new File(path + "images/student/");
				if (!newFile.exists())
					newFile.mkdirs();
				String name = file.getOriginalFilename();
				String wei = name.substring(name.lastIndexOf("."), name.length());
				String uuid = UUID.randomUUID().toString();
				String filePath = path + "images/student/" + uuid + wei;
				// ת���ļ�
				file.transferTo(new File(filePath));
				// // img·��
				// String imgUrl = ImagUtils.imgUrl();
				return "/student/" + uuid + wei;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return "err";
	}

	@RequestMapping("/update")
	@ResponseBody
	public void update(Student student,@RequestParam("xdianhua")String xdianhua) {
		
		studentService.update(student);
	System.out.println(student.toString()+"xdianhua");
		emergencyPersonService.updateemergencyPerson(student.getId(), xdianhua);
	}

	@RequiresPermissions("student:indexfind")
	@RequestMapping("/indexfind")
	public String indexfind() {

		return "studentfind/studentfind";
	}

	// ͨ���༶id��ȡ�ð�ѧ��
	@RequestMapping("/getByClassId")
	@ResponseBody
	public List<Student> getByClassId(Integer classId) {
		return studentService.getByClassId(classId);
	}

	// ͨ���༶id,רҵ�����ֻ�ȡѧ��
	@RequestMapping("/getByClassIdMaId")
	@ResponseBody
	public List<Student> getByClassIdMaId(Integer classId, Integer majorId, String studentName) {
		return studentService.getByClassIdMaId(classId, majorId, studentName);
	}

	// ͨ��ѧ��id��ȡһ��ѧ������
	@RequestMapping("/getById")
	@ResponseBody
	public Student getById(Integer id) {
		return studentService.getById(id);
	}

	@RequestMapping("/listAll")
	@ResponseBody
	public Object listAll(Page page, String name, String idCard) {
		return studentService.listAll(page, name, idCard);

	}

	// ����ģ����ѯ
	@RequestMapping("/getStudent")
	@ResponseBody
	public List<Student> getStudent(Student student) {
		System.out.println(student);
		return studentService.getStudent(student);
	}

	// ��ȡ�ð༶ѧ��������רҵ����
	@RequestMapping("/getClassStudents")
	@ResponseBody
	public List<Student> getClassStudents(Integer id) {
		return studentService.getClassStudents(id);
	}

	// ����ģ��༶id
	@RequestMapping("/updateTestClassId")
	@ResponseBody
	public void updateTestClassId(Integer testClassId, Integer[] studentIds) {
		studentService.updateTestClassId(testClassId, studentIds);
	}

	// ��ȡѧ����Ϣ
	@RequestMapping("/getName")
	@ResponseBody

	public List<Student> getName(Integer id) {

		return studentService.getName(id);
	}

	// ��ȡ����ģ��༶Id
	@RequestMapping("/listTestClassroomId")
	@ResponseBody
	public List<Integer> listTestClassroomId() {
		return studentService.listTestClassroomId();
	}

	// ͨ��ģ��༶Id��ȡѧ��
	@RequestMapping("/getByTestClassId")
	@ResponseBody
	public Object getByTestClassId(Page page, Integer testClassId) {
		return studentService.getByTestClassId(page, testClassId);
	}

	@RequestMapping("/select")
	@ResponseBody
	public List<Student> select(String name, String sex, String idcard) {
		List<Student> students = studentService.select(name, sex, idcard);

		return students;
	}

	@RequestMapping("/getSomeStudents")
	@ResponseBody
	public PageData getSoneStudents(Student student, String examName, Page page) {
		PageData someStudents = studentService.getSomeStudents(student, examName, page);
		System.out.println("************"+someStudents.getRows());
		return someStudents;
	}

	// �Ƴ�ģ��༶Id
	@RequestMapping("/deleteTestClassId")
	@ResponseBody
	public void deleteTestClassId(Integer id) {
		studentService.deleteTestClassId(id);
	}

	// ��ģ��༶����Ϊʵ�ʰ༶
	@RequestMapping("/updateClassId")
	@ResponseBody
	public void updateClassId(Integer testClassId, Integer[] studentIds) {
		studentService.updateClassId(testClassId, studentIds);
	}

	// ������
	@RequestMapping("/getAll")
	@ResponseBody

	public Object getAll(Page page, String stuName, String idCard) {

		return studentService.getAll(page, stuName, idCard);

	}

	// �������֤���ж��Ƿ��ظ�¼��

	// ������
	@RequestMapping("/IdcardisNo")
	@ResponseBody
	public Boolean idcardisNo(String idCard) {

		System.err.println(idCard);

		return studentService.idcardisNo(idCard);
	}

	// ɾ��ѧ��
	@RequestMapping("/delete")
	@ResponseBody
	public String delete(Student student) {
		return studentService.deleteStudent(student);
	}
//  ����excel
	@RequestMapping("/print1")
	@ResponseBody
	public String export(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception{
		String strDirPath = request.getSession().getServletContext().getRealPath("/"); 
		Page page=new Page();
		page.setPage(1);;page.setRows(10000);
		List<Student> lists=studentService.export();
		HSSFWorkbook wb=new HSSFWorkbook();//����HSSFWorkbook����
		HSSFSheet sheet=wb.createSheet("学生信息表");//����HSSFSheet����
		HSSFRow row1=sheet.createRow(0);//����HSSFRow����
		HSSFCell cell=row1.createCell(0);//����HSSFCell����
		cell.setCellValue("学生信息表");//���õ�Ԫ���ֵ
		//�ϲ���Ԫ��CellRangeAddress����������α�ʾ��ʼ�У������У���ʼ�У� ������
		sheet.addMergedRegion(new CellRangeAddress(0,0,0,10));
		//��sheet�ﴴ���ڶ���
		HSSFRow row2=sheet.createRow(1);    
		      //������Ԫ�����õ�Ԫ������
		      row2.createCell(0).setCellValue("姓名");
		      row2.createCell(1).setCellValue("入学时间");    
		      row2.createCell(2).setCellValue("班级");
		      row2.createCell(3).setCellValue("性别");
		      row2.createCell(4).setCellValue("年龄");
		      row2.createCell(5).setCellValue("住宿情况");
		      row2.createCell(6).setCellValue("身份证号");
		      row2.createCell(7).setCellValue("电话");
		      row2.createCell(8).setCellValue("是否就业");
		      row2.createCell(9).setCellValue("家长电话");
		      row2.createCell(10).setCellValue("地址");
		for(int i=3;i<lists.size()+3;i++){
			HSSFRow row=sheet.createRow(i-1);
			row.createCell(0).setCellValue(lists.get(i-3).getName());
			row.createCell(1).setCellValue(lists.get(i-3).getJoinTime());
			row.createCell(2).setCellValue(lists.get(i-3).getClassroom().getName());
			row.createCell(3).setCellValue(lists.get(i-3).getSex().getText());
			row.createCell(4).setCellValue(lists.get(i-3).getAge());
			row.createCell(5).setCellValue(lists.get(i-3).getDormitorynote());
			row.createCell(6).setCellValue(lists.get(i-3).getIdCard());	
			row.createCell(7).setCellValue(lists.get(i-3).getTel());
			row.createCell(8).setCellValue(lists.get(i-3).getFindJobText());
			row.createCell(9).setCellValue(lists.get(i-3).getPersons().get(0).getTel());
			row.createCell(10).setCellValue(lists.get(i-3).getAddress());
		}
		String uuid=UUID.randomUUID().toString().replace("-", "").toUpperCase();
		FileOutputStream output=new FileOutputStream(strDirPath+"static/xls/"+uuid+".xls");
	    wb.write(output);
	    output.flush();
	    wb.close();
		return "static/xls/"+uuid+".xls";

	    
	}
//  ��������excel
	@RequestMapping("/print2")
	@ResponseBody
	public String export2(HttpServletRequest request,HttpServletResponse response,Model model,Integer id
			) throws Exception{
		String strDirPath = request.getSession().getServletContext().getRealPath("/"); 
		Student lists=(studentService).export1(id);
		List<Fee> lists1=(feeService).export1(id);
		
		HSSFWorkbook wb=new HSSFWorkbook();//����HSSFWorkbook����
		HSSFSheet sheet1=wb.createSheet("学生信息详情");//����HSSFSheet����
		HSSFRow row1=sheet1.createRow(0);//����HSSFRow����
		HSSFCell cell=row1.createCell(0);//����HSSFCell����-
		cell.setCellValue("学生信息详情");//���õ�Ԫ���ֵ
		//�ϲ���Ԫ��CellRangeAddress����������α�ʾ��ʼ�У������У���ʼ�У� ������
		sheet1.addMergedRegion(new CellRangeAddress(0,0,0,14));
		//��sheet�ﴴ���ڶ���
		HSSFRow row2=sheet1.createRow(1);    
		      //������Ԫ�����õ�Ԫ������
		      row2.createCell(0).setCellValue("姓名");
		      row2.createCell(1).setCellValue("性别");    
		      row2.createCell(2).setCellValue("身份证号");
		      row2.createCell(3).setCellValue("年龄");
		      row2.createCell(4).setCellValue("出生日期");
		      row2.createCell(5).setCellValue("电话");
		      row2.createCell(6).setCellValue("入学时间");
		      row2.createCell(7).setCellValue("班级");
		      row2.createCell(8).setCellValue("专业");
		      row2.createCell(9).setCellValue("地址");
		      row2.createCell(10).setCellValue("学习时长");
		      row2.createCell(11).setCellValue("家长");
		      row2.createCell(12).setCellValue("家长电话");
		      row2.createCell(13).setCellValue("招生老师");
		      row2.createCell(14).setCellValue("老师电话");
		      row2.createCell(15).setCellValue("备注");
		
			HSSFRow row=sheet1.createRow(3-1);
			row.createCell(0).setCellValue(lists.getName());
			row.createCell(1).setCellValue(lists.getSex().getText());
			row.createCell(2).setCellValue(lists.getIdCard());	
			row.createCell(3).setCellValue(lists.getAge());
			row.createCell(4).setCellValue(lists.getBirthday());
			row.createCell(5).setCellValue(lists.getTel());
			row.createCell(6).setCellValue(lists.getJoinTime());
			row.createCell(7).setCellValue(lists.getClassroom().getName());
			row.createCell(8).setCellValue(lists.getMajor().getName());
			row.createCell(9).setCellValue(lists.getAddress());
			row.createCell(10).setCellValue(lists.getStudyTime());
			row.createCell(11).setCellValue(lists.getPersons().get(0).getName());
			row.createCell(12).setCellValue(lists.getPersons().get(0).getTel());
			row.createCell(13).setCellValue(lists.getProxyTeacher().getName());
			row.createCell(14).setCellValue(lists.getProxyTeacher().getTel());	
			row.createCell(15).setCellValue(lists.getRemarks());
			
			HSSFRow row3=sheet1.createRow(3);//����HSSFRow����
			HSSFCell cell1=row3.createCell(0);//����HSSFCell����
			cell1.setCellValue("缴费情况");//���õ�Ԫ���ֵ
			//�ϲ���Ԫ��CellRangeAddress����������α�ʾ��ʼ�У������У���ʼ�У� ������
			sheet1.addMergedRegion(new CellRangeAddress(3,3,0,4));
			//��sheet�ﴴ���ڶ���
			HSSFRow row4=sheet1.createRow(4);   
		      //������Ԫ�����õ�Ԫ������
		      row4.createCell(0).setCellValue("缴费类型");
		      row4.createCell(1).setCellValue("缴费方式");    
		      row4.createCell(2).setCellValue("缴费时间");
		      row4.createCell(3).setCellValue("缴费金额");
		      row4.createCell(4).setCellValue("备注");
		for(int i=6;i<lists1.size()+6;i++){
			HSSFRow row5=sheet1.createRow(i-1);
			row5.createCell(0).setCellValue(lists1.get(i-6).getType());
			row5.createCell(1).setCellValue(lists1.get(i-6).getPayment().getName());
			row5.createCell(2).setCellValue(lists1.get(i-6).getPaymentTime());
			row5.createCell(3).setCellValue(lists1.get(i-6).getAmount());
			row5.createCell(4).setCellValue(lists1.get(i-6).getRemarks());
		}
		List<Score> lists4 = scoreService.export1(id);
		List<Exam> lists2=examService.export1(id);
		List<Course> lists3=courseService.export1(id);
		int count = feeService.getFeeCountByStudentId(id);
		HSSFRow row6=sheet1.createRow(count+5);//����HSSFRow����
		HSSFCell cell2=row6.createCell(0);//����HSSFCell����
		cell2.setCellValue("各科考试成绩");//���õ�Ԫ���ֵ
		//�ϲ���Ԫ��CellRangeAddress����������α�ʾ��ʼ�У������У���ʼ�У� ������
		sheet1.addMergedRegion(new CellRangeAddress(count+5,count+5,0,8));
		//��sheet�ﴴ���ڶ���
		HSSFRow row7=sheet1.createRow(count+6);   
	      //������Ԫ�����õ�Ԫ������
	      row7.createCell(0).setCellValue("学生姓名");
	      row7.createCell(1).setCellValue("考试时间");    
	      row7.createCell(2).setCellValue("考试名称");
	      row7.createCell(3).setCellValue("JAVA基础");
	      row7.createCell(4).setCellValue("H5前端");
	      row7.createCell(5).setCellValue("MySql数据库");
	      row7.createCell(6).setCellValue("JAVAWEB基础");
	      row7.createCell(7).setCellValue("框架");
	      row7.createCell(8).setCellValue("实训");
	      
	      //各科成绩
	     HashMap<String, Score> map = new HashMap<String,Score>();
	/*for(int i=count+8;i<lists4.size()+count+8;i++){*/
		for(int j=count+8;j<lists2.size()+count+8;j++){
		HSSFRow row8=sheet1.createRow(j-1);
		row8.createCell(0).setCellValue(lists2.get(j-count-8).getStudent().getName());
		row8.createCell(1).setCellValue(lists2.get(j-count-8).getExamDate());
		row8.createCell(2).setCellValue(lists2.get(j-count-8).getName());
		for (Score score : lists4) {
	    	 for (Course course : lists3) {
	 			if(course.getId()==score.getCourse().getId()){
	 				map.put(course.getName(), score);
	 			}
	 		}
		}
		//获取键 （课程名字）
		Set<String> set = map.keySet();
		for (String key : set) {
			//获取值 （成绩）
			Score score = map.get(key);
			if(key.equals("JAVA基础")){
				row8.createCell(3).setCellValue(score.getScore());
			}else if(key.equals("H5前端")){
				row8.createCell(4).setCellValue(score.getScore());
			}else if(key.equals("MySQL数据库")){
				row8.createCell(5).setCellValue(score.getScore());
			}else if(key.equals("JAVAWEB基础")){
				row8.createCell(6).setCellValue(score.getScore());
			}else if(key.equals("框架")){
				row8.createCell(7).setCellValue(score.getScore());
			}else if(key.equals("实训")){
				row8.createCell(8).setCellValue(score.getScore());
			}
		}
	}
//	}
		String uuid=UUID.randomUUID().toString().replace("-", "").toUpperCase();
		FileOutputStream output=new FileOutputStream(strDirPath+"static/xls/"+uuid+".xls");
	    wb.write(output);
	    output.flush();
	    wb.close();
		return "static/xls/"+uuid+".xls";

	    
	}
	//查所有//下拉框
	@RequestMapping("/findAll")
	@ResponseBody
	public List<Student> findAll() {
		return studentService.findAll();
	}
	//通过名字查找电话
		@RequestMapping("/findStuByName")
		@ResponseBody
		public List<Student> findStuByName(Student student) {
			return studentService.findStuByName(student);
		}
}
