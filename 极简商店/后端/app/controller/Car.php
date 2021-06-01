<?php


namespace app\controller;

use app\common\Token;
use app\model\Car as CarModel;
use app\model\commodity as CommodityModel;
use think\Request;

class Car
{
    public function __construct(Request $request)
    {
        $this->require = $request;
    }

    public function addCar(){
        $token = $this->require->param('token');
        $id = $this->require->param('id');
        $query = CarModel::where('user_id',Token::checkToken($token))
        ->where('com_id',$id)->limit(1)->find();
        if (empty($query)) {
            $status = CarModel::insert([
                'user_id' => Token::checkToken($token),
                'com_id' => $id,
                'num' => 1,
                'add_time' => date("Y-m-d H:i:s")
            ]);
        }else{
            $status=$query->inc('num')->update();
        }
        return json(['status' =>$status]);
    }
    public function cars(){
        $token = $this->require->param('token');
        $query = CarModel::where('user_id',Token::checkToken($token))->select();
        foreach ($query as $obj){
            $obj->name = CommodityModel::where('id',$obj->com_id)->limit(1)->value('name');
        }
        return json($query);
    }
    public function setNum(){
        $token = $this->require->param('token');
        $id = $this->require->param('id');
        $num = $this->require->param('num');
        $status = CarModel::where('user_id',Token::checkToken($token))
            ->where('com_id',$id)->limit(1)->update(['num' => $num]);
        return json(['status' =>$status]);
    }
    public function delCar(){
        $token = $this->require->param('token');
        $id = $this->require->param('id');
        $status = CarModel::where('user_id',Token::checkToken($token))
            ->where('com_id',$id)->limit(1)->delete();
        return json(['status' =>$status]);
    }
}