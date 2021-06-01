<?php
declare (strict_types = 1);

namespace app\validate;

use think\Validate;

class User extends Validate
{
    /**
     * 定义验证规则
     * 格式：'字段名' =>  ['规则1','规则2'...]
     *
     * @var array
     */
    protected $rule = [
        'phone' => 'require|number|max:11',
        'password' => 'require|min:6',
    ];

    /**
     * 定义错误信息
     * 格式：'字段名.规则名' =>  '错误信息'
     *
     * @var array
     */
    protected $message = [
        'phone.require' => '电话号码必填',
        'phone.number' => '电话号码只能为数字',
        'phone.max' => '电话号码最多11位',
        'password.require' => '密码必填',
        'password.min' => '密码至少6位',
    ];
}
