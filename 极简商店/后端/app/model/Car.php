<?php


namespace app\model;

use think\Model;


class Car extends Model
{
    protected $table='store_car';
    protected $field=['name','price','inventory','introduction','detail','tsales','msales'];
}