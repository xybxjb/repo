package cn.deepcoding.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.deepcoding.dao.FeeDao;
import cn.deepcoding.dao.FeeDueTimeDao;
import cn.deepcoding.dao.FeeTypeDao;
import cn.deepcoding.dao.StudentDao;
import cn.deepcoding.model.Fee;
import cn.deepcoding.model.FeeDueTime;
import cn.deepcoding.model.FeeType;
import cn.deepcoding.model.Student;
import cn.deepcoding.service.FeeService;
import cn.deepcoding.service.ProxyCommissionService;

@Service
@Transactional
public class FeeServiceImpl implements FeeService {

	@Autowired
	private FeeDao feeDao;
	@Autowired
	private FeeTypeDao feeTypeDao;
	
	@Autowired 
	private StudentDao studentDao;
	
	@Autowired
	private FeeDueTimeDao feeDueTimeDao;
	
	@Autowired
	private ProxyCommissionService proxyCommissionService;
 
	//王晓宇
	public void save(Fee fee) {
		/*System.err.println(fee.getStudent().getName());
		System.err.println(fee.getStudent().getId());
		
		Integer continuePay = null;
		Integer num = null;
		Student student = studentDao.getOneStudent(fee.getStudent().getId());//学生表信息  拿到学费/杂费/住宿费总额
		String count = feeDao.countTuition(fee.getStudent().getId(), fee.getType());//费用表该类型已交总额
		
		if (fee.getType().equals("学费")) {//如果费用类型是学费
			if (count == null) { //如果没有数据
				if(student.getTuition() > fee.getAmount()){
					continuePay = 0;//0是未缴清
				}else{
					continuePay = 1;
				}
				
			} else if (student.getTuition() > (Double.parseDouble(count)+fee.getAmount())) { //如果学生应交学费大于交费的总合
				continuePay = 0;//0是未缴清
			} else {
				continuePay = 1;//1是已缴清
			}
		}else if (fee.getType().equals("杂费")) {//如果费用类型是杂费
			if (count == null) {
				if(student.getIncidentals() > fee.getAmount()){
					continuePay = 0;//0是未缴清
				}else{
					continuePay = 1;
				}
				
			} else if (student.getIncidentals() > (Double.parseDouble(count)+fee.getAmount())) {
				continuePay = 0;
			} else {
				continuePay = 1;
			}
		}else if (fee.getType().equals("住宿费")) {//如果费用类型是住宿费
			int dou = (int)fee.getAmount();  //拿到交的住宿费
			Integer amount = Integer.valueOf(dou);
			num = amount/550;//算出交了几个月住宿费
		}		
		//所缴类型在fee_due_time是否有记录
		Integer i = feeDueTimeDao.feeDueExist(fee.getStudent().getId(),fee.getType());
		if (i == 0) { 
			if (num == null) {
				feeDueTimeDao.saveTuition(fee.getType(),null, fee.getStudent().getId(),null);
			}else {
				Calendar cal = Calendar.getInstance();
				//下面的就是把当前日期加X个月
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
				cal.add(Calendar.MONTH, num);
				feeDueTimeDao.saveTuition(fee.getType(),cal.getTime(), fee.getStudent().getId(),null);
			}
		}else {
			
			if (num == null) {
				feeDueTimeDao.updateTuition(fee.getType(),null,fee.getStudent().getId(),continuePay);
			}else {
				feeDueTimeDao.updateTuition(fee.getType(),num,fee.getStudent().getId(),continuePay);
			}
			
		}
		feeDao.save(fee);*/
	}
	
	
	
	
	
	
	//王晓宇
	public List<Fee> select(Integer id) {
		
		return  feeDao.select(id);
	}
	@Override
	public Fee selectFee(int studentId) {

		return (Fee)feeDao.selectFee(studentId);

		//return feeDao.selectFee(studentId);

	}

	@Override
	public List<Fee> export1(Integer id) {
		// TODO Auto-generated method stub
		return feeDao.select(id);
	}



	 // 根据学生 id 查询部分信息的个数
	@Override
	public int getFeeCountByStudentId(Integer id) {
		// TODO Auto-generated method stub
		return feeDao.getFeeCountByStudentId(id);
	}


	@Override
	public void save(Fee fee, Integer [] feeTypeId, double[] amount, java.sql.Date[] endDate) {
		int di = 0;
		if(feeTypeId!=null&&feeTypeId.length>0){
			for(int i = 0;i<feeTypeId.length;i++){
				FeeType feeType = feeTypeDao.get(feeTypeId[i]);
				if(feeType!=null && feeType.getName().equals("学费")){
					fee.setType(feeType.getName());
					fee.setAmount(amount[i]);
					proxyCommissionService.save(fee);

				}
				Integer continuePay = null;
				Integer num = null;
				Student student = studentDao.getOneStudent(fee.getStudent().getId());//学生表信息  拿到学费/杂费/住宿费总额
				String count = feeDao.countTuition(fee.getStudent().getId(), feeType.getName());//费用表该类型已交总额
				
				fee.setType(feeType.getName());
				fee.setAmount(amount[i]);
				fee.setFeeType(feeType);
				if(feeType.getAddDate()) {
					fee.setEndDate(endDate[di]);
				}
				FeeDueTime feeDueTime = feeDueTimeDao.feeDueExist(fee.getStudent().getId(),feeType.getName());
				
				if(feeDueTime!=null) {
					if(feeType.getAddDate()) {
						feeDueTimeDao.updateFeeDueTime(feeType.getName(), endDate[di], fee.getStudent().getId(), null);
					}else {
						feeDueTimeDao.updateFeeDueTime(feeType.getName(), null, fee.getStudent().getId(), null);
					}
				}else {
					feeDueTime = new FeeDueTime();
					feeDueTime.setContinuePay(null);
					if(feeType.getAddDate()) {
						feeDueTime.setEntDate(endDate[di]);
					}
					feeDueTime.setStudent(student);
					feeDueTime.setType(feeType.getName());
					//feeDueTimeDao.saveTuition(feeType.getName(),endDate[di], fee.getStudent().getId(),null);
					feeDueTimeDao.save(feeDueTime);
				}
				fee.setFeeDueTime(feeDueTime);
				feeDao.save(fee);
				
				if(feeType.getAddDate()) {
					di++;
				}
				
			}
		}
		
	}




	/*
	 * @Override public void save(Fee fee, String[] type, double[] amount,
	 * java.sql.Date entDate) { if(type!=null){ System.err.println(type); for(int i
	 * = 0;i<type.length;i++){ if(type[i].equals("学费")){ fee.setType(type[i]);
	 * fee.setAmount(amount[i]); proxyCommissionService.save(fee); } } } // TODO
	 * Auto-generated method stub
	 * System.err.println("==========================="+type); for(int
	 * i=0;i<type.length;i++){ Integer continuePay = null; Integer num = null;
	 * Student student = studentDao.getOneStudent(fee.getStudent().getId());//学生表信息
	 * 拿到学费/杂费/住宿费总额 String count = feeDao.countTuition(fee.getStudent().getId(),
	 * type[i]);//费用表该类型已交总额
	 * 
	 * if (type[i].equals("学费")) {//如果费用类型是学费 fee.setType(type[i]);
	 * fee.setAmount(amount[i]); feeDao.save(fee); if (count == null) { //如果没有数据
	 * 
	 * if(student.getTuition() > amount[i]){ continuePay = 0;//0是未缴清 }else{
	 * continuePay = 1; }
	 * 
	 * } else if (student.getTuition() > (Double.parseDouble(count)+amount[i])) {
	 * //如果学生应交学费大于交费的总合 continuePay = 0;//0是未缴清 } else { continuePay = 1;//1是已缴清 }
	 * }else if (type[i].equals("杂费")) {//如果费用类型是杂费 fee.setType(type[i]);
	 * fee.setAmount(amount[i]); feeDao.save(fee); if (count == null) {
	 * if(student.getIncidentals() > amount[i]){ continuePay = 0;//0是未缴清 }else{
	 * continuePay = 1; }
	 * 
	 * } else if (student.getIncidentals() > (Double.parseDouble(count)+amount[i]))
	 * { continuePay = 0; } else { continuePay = 1; } }else
	 * if(type[i].equals("住宿费")){ fee.setType(type[i]); fee.setAmount(amount[i]);
	 * fee.setInitialResideDate(entDate.toString()); feeDao.save(fee); }
	 * System.err.println(
	 * "--------------------------------------------------------1321456468");
	 * 
	 * //所缴类型在fee_due_time是否有记录 Integer j =
	 * feeDueTimeDao.feeDueExist(fee.getStudent().getId(),type[i]); if (j == 0) { if
	 * (!type[i].equals("住宿费")) { feeDueTimeDao.saveTuition(type[i],null,
	 * fee.getStudent().getId(),null); }else {
	 * 
	 * feeDueTimeDao.saveTuition(type[i],entDate, fee.getStudent().getId(),null); }
	 * }else {
	 * 
	 * if (!type[i].equals("住宿费")) {
	 * feeDueTimeDao.updateTuition(type[i],null,fee.getStudent().getId(),continuePay
	 * ); }else {
	 * //feeDueTimeDao.updateTuition(type[i],num,fee.getStudent().getId(),
	 * continuePay);
	 * feeDueTimeDao.updateTuition2(type[i],entDate,fee.getStudent().getId(),
	 * continuePay); }
	 * 
	 * }
	 * 
	 * }
	 * 
	 * }
	 */


	@Override
	public Fee getInittime(Integer id) {
		// TODO Auto-generated method stub
		return feeDao.getInittime(id);
	}






	











	


 
}
