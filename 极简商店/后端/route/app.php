<?php
// +----------------------------------------------------------------------
// | ThinkPHP [ WE CAN DO IT JUST THINK ]
// +----------------------------------------------------------------------
// | Copyright (c) 2006~2018 http://thinkphp.cn All rights reserved.
// +----------------------------------------------------------------------
// | Licensed ( http://www.apache.org/licenses/LICENSE-2.0 )
// +----------------------------------------------------------------------
// | Author: liu21st <liu21st@gmail.com>
// +----------------------------------------------------------------------
use think\facade\Route;

//Route::get('think', function () {
//    return 'hello,ThinkPHP6!';
//});
//
Route::allowCrossDomain();

Route::get('hello/:name', 'index/hello');


//user api
Route::post('login','user/login');
Route::post('register','user/register');
Route::post('userinfo','user/getUserInfo');
Route::post('uploadprofilephoto','user/uploadprofilephoto');
Route::post('updateprofilephoto','user/updateprofilephoto');
Route::post('changeaddress','user/changeaddress');

//commodity api
Route::get('show','commodity/show');
Route::get('detail/:id','commodity/detail');

//order api
Route::post('carorder','order/carOrder');
Route::post('createorder','order/createOrder');
Route::post('getorders','order/getOrders');
Route::post('pay','order/pay');
Route::post('delorder','order/delOrder');

//car api
Route::post('addcar','car/addCar');
Route::post('cars','car/cars');
Route::post('setnum','car/setNum');
Route::post('delcar','car/delCar');