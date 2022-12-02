const isAuthenticated = () => {
    const accessToken = JSON.parse(localStorage.getItem('access_token'));
    if(accessToken){
        const hasExpired = accessToken.expiry < Math.trunc(new Date().getTime()/1000); 
        
        if(hasExpired){
            localStorage.removeItem('access_token');
            localStorage.removeItem('refresh_token');
            localStorage.removeItem('user');
            return false
        }

        return true;
    }

    return false;
}

export default isAuthenticated;