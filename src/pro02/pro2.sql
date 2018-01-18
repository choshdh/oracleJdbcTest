/*
1)	평균 연봉(salary)이 가장 높은 나라는?
 */

select rownum,
        country_name
from
    (select country_name,
            avg(salary) "평균급여"
    from employees es, departments ds, locations ls, countries cs
    where es.department_id = ds.department_id
          and ds.location_id = ls.location_id
          and ls.country_id = cs.country_id
    group by country_name
    order by "평균급여" desc)
where rownum = 1;


/*
2)	평균 연봉(salary)이 가장 높은 지역은?
*/

select rownum,
        region_name
from
    (select region_name,
            avg(salary) "평균급여"
    from employees es, departments ds, locations ls, countries cs, regions rs
    where es.department_id = ds.department_id
          and ds.location_id = ls.location_id
          and ls.country_id = cs.country_id
          and cs.region_id = rs.region_id
    group by region_name
    order by "평균급여" desc)
where rownum = 1;


/*
3)	가장 많은 직원이 있는 부서는 어떤 부서인가요?
*/

select rownum,
        department_name
from 
     (select  ds.department_name,
             count(employee_id) "사원수"
      from employees es , departments ds
      where es.department_id = ds.department_id
            and ds.department_id is not null
      group by ds.department_name
      order by "사원수" desc)
where rownum = 1;

