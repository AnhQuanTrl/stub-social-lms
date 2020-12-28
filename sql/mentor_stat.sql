CREATE MATERIALIZED VIEW mentor_stat
AS
select 
    u.id,
    count(distinct c.id) as course_number,
	count(distinct mt.user_id) as student_number,
	avg(mt.rating) as student_rating
from 
    users u,
    mentor m,
    course c,
	mentee mt
where 
    u.id = m.user_id
	and m.course_id = c.id
	and c.id = mt.course_id
group by u.id
order by u.id
WITH NO DATA;
CREATE UNIQUE INDEX mentor_stat_uk ON mentor_stat (id);