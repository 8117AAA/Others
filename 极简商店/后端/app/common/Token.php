<?php


namespace app\common;


use Firebase\JWT\JWT;

class Token
{
    public static function signToken($id)
    {
        $key = env('token.key','salt');
        $token = array(
            "iss" => $key,
            "aud" => '',
            "iat" => time(),
            "nbf" => time(),
            "exp" => time() + env('token.exp',86400),
            "data" => [
                'id' => $id,
            ]
        );
        $jwt = JWT::encode($token, $key, "HS256");
        return $jwt;
    }

    public static function checkToken($token)
    {
        $key = env('token.key','salt');
        try {
//            JWT::$leeway = 60;
            $decoded = JWT::decode($token, $key, array('HS256')); //HS256方式，这里要和签发的时候对应
            return $decoded->data->id;

        } catch (\Firebase\JWT\SignatureInvalidException $e) { //签名不正确
            return "签名不正确";
        } catch (\Firebase\JWT\BeforeValidException $e) { // 签名在某个时间点之后才能用
            return "token失效";
        } catch (\Firebase\JWT\ExpiredException $e) { // token过期
            return "token失效";
        } catch (Exception $e) { //其他错误
            return "未知错误";
        }
    }
}