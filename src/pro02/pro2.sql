/*
1)	평균 연봉(salary)이 가장 높은 나라는?
 */
--나라이름 ,평균 연봉 출력

select 나라이름,
       평균연봉
from
     (select rownum,
             나라이름,
             평균연봉
      from
          (select country_name as 나라이름,
                  avg(salary) as 평균연봉
           from employees es, departments ds, locations ls, countries cs
           where es.department_id = ds.department_id
                 and ds.location_id = ls.location_id
                 and ls.country_id = cs.country_id
           group by country_name
           order by 평균연봉 desc)
      where rownum = 1);

/*
2)	평균 연봉(salary)이 가장 높은 지역은?
*/
--지역이름, 평균 연봉 출력

select 지역이름,
       평균연봉
from
     (select rownum,
             region_name as 지역이름,
             salary as 평균연봉
      from
           (select region_name ,
                    avg(salary) salary
            from employees es, departments ds, locations ls, countries cs, regions rs
            where es.department_id = ds.department_id
                  and ds.location_id = ls.location_id
                  and ls.country_id = cs.country_id
                  and cs.region_id = rs.region_id
            group by region_name
            order by salary desc)
      where rownum = 1);


/*
3)	가장 많은 직원이 있는 부서는 어떤 부서인가요?
*/
--부서이름, 직원수
select "부서이름",
		"사원수"
from 
	 (select rownum,
	        "부서이름",
	        "사원수"
	  from 
	       (select  ds.department_name "부서이름",
	             count(employee_id) "사원수"
	        from employees es , departments ds
	        where es.department_id = ds.department_id
	            and ds.department_id is not null
	        group by ds.department_name
	        order by "사원수" desc)
	  where rownum = 1);

