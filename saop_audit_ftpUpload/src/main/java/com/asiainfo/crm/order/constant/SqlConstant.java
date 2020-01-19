package com.asiainfo.crm.order.constant;

/**
 * @author: dong.chao
 * @create: 2019-12-15 15:43
 * @description: 处理订单SQL
 **/
public class SqlConstant {

    /**
     * 处理堵单情况SQL
     * */
    public static final  String WALL_ORDER_SQL = "  update   message_order mo set mo.process_state = 'I'  where mo.process_state = 'W' and mo.business_flag = '888888' ";

    /**
     * 需要重送订单情况SQL
     * */
    public static final  String ON_WAY_ORDER_SQL = " begin for rec in (   select mo.* from message_order mo where mo.process_state = 'E1' and ( mo.process_result like '%在途单%'   or mo.process_result like '%规则异常%' or mo.process_result like '%anId%'  or mo.process_result like '%全局锁失败%' or mo.process_result like '%数据状态不为W%' or mo.process_result like '%socket超时异常%' or mo.process_result like '%计费接口收费异常%' )  ) loop  update message_order mo SET mo.process_state = 'W' where mo.id = rec.id;  update listen_message_order lmo set lmo.deal_flag = 'D' where  lmo.msg_content_key = rec.id; end loop; end; ";


    /**
     * 需要重送订单情况SQL 限制30条
     * */
    public static final  String ON_WAY_E1_ORDER_SQL = " begin  for rec in (   select * from message_order mo where mo.process_state = 'E1' and mo.isale is null and mo.process_result like '%未归档%' and rownum <= 30  ) loop   update message_order mo SET mo.process_state = 'W' where mo.id = rec.id; update listen_message_order lmo set lmo.deal_flag = 'D' where  lmo.msg_content_key = rec.id; end loop;  end; ";

    /**
     * 重送A或W状态没有改过来的(即Listen_message_order为F失败的)
     */
    public static final String ON_WAY_A_W_ORDER_SQL = " begin  for rec in (    select mo.id from message_order mo join  listen_message_order  lmo on    mo.id = lmo.msg_content_key  where mo.process_state in ( 'A' , 'W') and lmo.deal_flag = 'F'    ) loop update message_order mo SET mo.process_state = 'W', mo.ol_id = ''  where mo.id = rec.id;  update listen_message_order lmo set lmo.deal_flag = 'D' where  lmo.msg_content_key = rec.id; end loop;  end;";

}
