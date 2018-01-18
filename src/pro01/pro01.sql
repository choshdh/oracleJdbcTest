/*
1) EMPLOYEE 테이블에서 이름(Last Name)에 “hae”를 포함하고 있는 사원들과 같은 부서에서 근무하고 있는 사원의 
EMPLOYEE_ID, FIRST_NAME, LAST_NAME, DEPARTMENT_ID 를 출력하라. 
*/

select employee_id,
        first_name,
        last_name,
        department_id
from employees
where department_id = (select  department_id
                       from employees
                       where last_name like '%hae%')
order by employee_id desc;


/*
2) 각 도시(city)별 가장 연봉을 많이 받고 있는 사원의 도시 이름(City), First Name, Last Name, 급여를 조회하라. 
급여 순으로 오름차순 정렬하시오. (1-2.sql)
*/  

select  city,
        first_name,
        last_name,
        salary
from employees es, departments ds, locations ls
where es.department_id = ds.department_id
      and ds.location_id = ls.location_id
      and (city ,salary) in (select  city,
                             max(salary)
                     from employees es,departments ds, locations ls
                     where es.department_id = ds.department_id and ds.location_id = ls.location_id
                     group by city)
order by salary;