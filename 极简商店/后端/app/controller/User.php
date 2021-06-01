<?php


namespace app\controller;

use app\common\Token;
use app\model\User as UserModel;
use think\exception\ValidateException;
use think\Request;
use think\facade\Filesystem;

class User
{
    public function __construct(Request $request)
    {
        $this->require = $request;
    }
    public function register()
    {
        $data = [
            'phone' => $this->require->param('phone'),
            'password' => $this->require->param('password'),
            'name' => $this->require->param('name'),
            'address' => $this->require->param('address')
        ];
        try {
            $result = validate(\app\validate\User::class)->batch(true)->check($data);
        } catch (ValidateException $e) {
            return json(['msg' => $e->getError()]);
        }
        return json(['msg' => UserModel::insert($data)]);
    }

    public function login()
    {
        $userid = UserModel::where('phone', $this->require->param('phone'))
            ->where('password', $this->require->param('password'))
            ->value('id');
        $token = Token::signToken($userid);
        return empty($userid) ? json(['msg' => '账号或者密码错误']) : json(['token' => $token]);
    }
    public function getUserInfo()
    {
        $token = $this->require->param('token');
        $user = UserModel::where('id', Token::checkToken($token))
            ->column(['phone', 'name', 'address', 'profile_photo']);
        return json($user[0]);
    }
    public function uploadProfilePhoto()
    {
        $file = request()->file('image');
        return json(['url' => Filesystem::disk('public')->putFile('profile_photo', $file, 'md5')]);
    }
    public function updateProfilePhoto()
    {
        $token = $this->require->param('token');
        $img = $this->require->param('image');
        return json(['status'=> UserModel::where('id', Token::checkToken($token))->update(['profile_photo' => $img]) ]);
    }
    public function changeAddress(){
        $token = $this->require->param('token');
        $address = $this->require->param('address');
        return json(['status'=> UserModel::where('id', Token::checkToken($token))->update(['address' => $address]) ]);
    }
}