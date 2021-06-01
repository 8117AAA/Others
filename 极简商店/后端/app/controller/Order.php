<?php


namespace app\controller;

use app\common\Token;
use app\model\Order as OrderModel;
use app\model\Commodity as CommodityModel;
use app\model\Car as CarModel;
use think\Request;

class Order
{
    public function __construct(Request $request)
    {
        $this->require = $request;
    }

    public function carOrder(){
        $token = $this->require->param('token');
        $car = CarModel::where('user_id',Token::checkToken($token))->select();
        $comm_id = '';
        $price = 0;
        foreach ($car as $obj) {
            $comm_id = $comm_id . $obj->com_id.'-'.$obj->num . ',';
            $obj->price = CommodityModel::where('id',$obj->com_id)->value('price');
            CarModel::where('com_id',$obj->com_id)
                ->where('user_id',Token::checkToken($token))->delete();
            $price += $obj->price * $obj->num;
        }
        $status = OrderModel::insert([
            'user_id' => Token::checkToken($token),
            'comm_id' => $comm_id,
            'price' => $price,
            'create_time' => date("Y-m-d H:i:s")
        ]);
        return $status;
    }

    public function createOrder()
    {
        $token = $this->require->param('token');
        $comm_id = $this->require->param('comm_id');
        $commodity = CommodityModel::where('id', $comm_id)
            ->limit(1)
            ->column(['name', 'price', 'inventory', 'introduction']);
        $status = OrderModel::insert([
            'user_id' => Token::checkToken($token),
            'comm_id' => $comm_id,
            'price' => $commodity[0]['price'],
            'create_time' => date("Y-m-d H:i:s")
        ]);
        return $status;
    }

    public function getOrders(){
        $token = $this->require->param('token');
        $orders = OrderModel::where('user_id',Token::checkToken($token))
            ->order('id')->select();
        foreach ($orders as $order){
            $order->name = CommodityModel::where('id',$order->comm_id)->limit(1)->value('name');
        }
        return json($orders);
    }

    public function pay()
    {
        $token = $this->require->param('token');
        $order_id = $this->require->param('order');
        $status = false;
        $query = OrderModel::where('id', $order_id)
            ->where('user_id', Token::checkToken($token))
            ->limit(1);
        if(empty($query->value('pay_time'))) {
           $query->update(['pay_time' => date("Y-m-d H:i:s")]);
           $status = true;
        }
        return json(['status'=>$status]);
    }

    public function delOrder(){
        $token = $this->require->param('token');
        $order_id = $this->require->param('order');
        $status = OrderModel::where('id', $order_id)
            ->where('user_id', Token::checkToken($token))
            ->delete();
        return json(['status'=>$status]);
    }
}