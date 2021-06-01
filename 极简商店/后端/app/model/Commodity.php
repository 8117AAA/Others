<?php


namespace app\model;

use think\Model;

class commodity extends Model
{
    protected $pk='id';
    protected $table='store_commodity';
    protected $field=['name','price','inventory','introduction','detail','tsales','msales'];
}