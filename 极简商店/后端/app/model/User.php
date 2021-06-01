<?php


namespace app\model;

use think\Model;


class User extends Model
{
    protected $pk='id';
    protected $table='store_user';
    protected $field=['phone','password','name','address','profile_photo'];
}