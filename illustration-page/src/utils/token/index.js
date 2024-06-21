// 存储token
export function setToken(tokenKey, token) {
    sessionStorage.setItem('isAuthenticated', true);
    return sessionStorage.setItem(tokenKey, token);
}
// 获取token
export function getToken(tokenKey) {
    return sessionStorage.getItem(tokenKey);
}
// 移除token
export function removeToken(tokenKey) {
    if (getToken(tokenKey)) {
        sessionStorage.setItem('isAuthenticated', false);
        return sessionStorage.removeItem(tokenKey);
    }
    return null;
}
// 存储登录