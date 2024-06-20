// 存储token
export function setToken(tokenKey, token) {
    console.log("测试2")
    return sessionStorage.setItem(tokenKey, token);
}
// 获取token
export function getToken(tokenKey) {
    console.log("测试3")
    return sessionStorage.getItem(tokenKey);
}
// 移除token
export function removeToken(tokenKey) {
    if (getToken(tokenKey)) {
        return sessionStorage.removeItem(tokenKey);
    }
    return null;
}