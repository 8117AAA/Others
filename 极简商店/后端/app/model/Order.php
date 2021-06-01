<?php


namespace app\model;

use \think\Model;

class Order extends Model
{
    protected $pk='id';
    protected $table='store_order';
    protected $field=['user_id','comm_id','price','create_time','pay_time'];
}