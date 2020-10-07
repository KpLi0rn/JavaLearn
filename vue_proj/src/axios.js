// 配置axios的前后置拦截
import axios from 'axios'
import ElementUI from 'element-ui'
import store from './store'
import router from './router'
import el from "element-ui/src/locale/lang/el";

axios.defaults.baseURL = "http://localhost:8088"

// 前置拦截
axios.interceptors.request.use(config =>{
    if(localStorage.token){
        config.headers.Authorization = localStorage.token;  //将token设置成请求头
    }
    return config
})

// 后置拦截
axios.interceptors.response.use(response => {
    let res = response.data
    if(res.code === 200){
        return response
    } else {
        ElementUI.Message.error('用户名或密码错误')
        return Promise.reject(response.data.message)
    }
},
    error => {
        console.log(error.response.data)
        if(error.response.data){
            error.message = error.response.data.message
        }
        console.log(error.message)
        // shiro 的权限认证
        if(error.response.status === 401){
            store.commit("REMOVE_INFO")
            error.message = "权限校验失败,请重新登录!"
            router.push('/login')
        }
        ElementUI.Message.error(error.message)
        return Promise.reject(error)
    }
)