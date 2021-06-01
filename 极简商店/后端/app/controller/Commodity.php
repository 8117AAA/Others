<?php


namespace app\controller;
use \app\model\commodity as commodityModel;

class Commodity
{
    public function show(){
        return json(commodityModel::limit(10)->column(['id','name','price','inventory','introduction','msales']));
    }
    public function detail($id){
        return json(commodityModel::where('id',$id)->column(['id','name','price','inventory','introduction','detail','msales'])[0]);
    }
}