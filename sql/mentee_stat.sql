CREATE MATERIALIZED VIEW mentee_stat
AS
select 
    u.id as user_id,
	c.id as course_id,
    avg(cip.final_score) as average_score,
	100 * COUNT(CASE WHEN cip.is_completed THEN 1 END) / greatest(COUNT(ci.id), 1) as progress
from 
    users u,
    mentee m,
    course c,
	module md,
	course_item ci,
	course_item_progress cip
where 
    u.id = m.user_id
	and m.course_id = c.id
	and md.course_id = c.id
	and ci.module_id = md.id
	and cip.course_item_id = ci.id
	and cip.user_id = u.id
group by u.id, c.id
order by u.id, c.id
WITH NO DATA;
CREATE UNIQUE INDEX mentee_stat_uk ON mentee_stat (user_id, course_id);