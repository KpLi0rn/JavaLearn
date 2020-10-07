import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    token: '',
    userId: ''
  },
  // 通过下面这个方法来进行修改
  mutations: {
    SET_TOKEN: (state, token) =>{
      state.token = token
      // 放到我们的localstore
      localStorage.setItem("token",token)
    },
    SET_USERID: (state, userId) =>{
      state.userId = userId
      // 放到我们的localstore
      sessionStorage.setItem("userId",userId)
    },
    // 删除
    REMOVE_INFO: (state) =>{
      state.token = ''
      state.userId = ''
      localStorage.setItem("token",'')
      sessionStorage.setItem("userId",'')
    },
  },
  getters: {
    getUser: state => {
      return state.userId
    }
  },
  actions: {
  },
  modules: {
  }
})
