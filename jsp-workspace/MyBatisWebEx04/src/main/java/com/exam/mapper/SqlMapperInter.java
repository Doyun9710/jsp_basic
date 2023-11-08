package com.exam.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.exam.model1.DeptTO;
import com.exam.model1.ZipcodeTO;

public interface SqlMapperInter {
	
	@Select( "select deptno, dname, loc from dept where deptno = 10" )
	public DeptTO selectByDeptno();
	
	@Select( "select deptno, dname, loc from dept" )
	public List<DeptTO> selectList();
	
	@Select( "select deptno, dname, loc from dept where deptno = #{ deptno }" )
	public List<DeptTO> selectListByDeptno(DeptTO to);
	
	@Select( "select deptno, dname, loc from dept where dname like #{ dname }" )
	public List<DeptTO> selectListByLikeDname(String dname);
	
	@Insert( "insert into dept2 values ( #{deptno}, #{dname}, #{loc} )" )
	public int insert(DeptTO to);
	
	@Update( "update dept2 set dname=#{ dname } where deptno=#{ deptno }")
	public int update(DeptTO to);
	
	@Delete( "delete from dept2 where deptno=#{ deptno }" )
	public int delete(String deptno);
	
	
	@Select( "select zipcode, sido, gugun, dong, ri, bunji from zipcode where dong like concat( #{ dong }, '%' )" )
	public List<ZipcodeTO> selectListZipcode(String dong);

}
