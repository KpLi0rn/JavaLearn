<template>
    <div>
        <el-container>
            <el-header>Login</el-header>
            <el-main>
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item label="用户名" prop="username">
                        <el-input v-model="ruleForm.username"></el-input>
                    </el-form-item>
                    <el-form-item label="密码" prop="password">
                        <el-input type="password" v-model="ruleForm.password"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" @click="submitForm('ruleForm')">立即登录</el-button>
                        <el-button @click="resetForm('ruleForm')">注册</el-button>
                    </el-form-item>
                </el-form>
            </el-main>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: "Login",
        data() {
            return {
                ruleForm: {
                    username: '',
                    password: '',
                },
                rules: {
                    username: [
                        { required: true, message: '请输入用户名', trigger: 'blur' },
                        { min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur' }
                    ],
                    password: [
                        { required: true, message: '请输入密码', trigger: 'change' },
                        { min: 3, max: 20, message: '长度在 3 到 20 个字符', trigger: 'blur' }

                    ]
                }
            };
        },
        methods: {
            submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        const _this = this
                        this.$axios.post( '/doLogin',this.ruleForm).then(res=>{
                            // 定义一个jwt的对象
                            const jwt = res.headers['authorization']
                            const userId = res.data.data  // 获取返回数据里面的用户id
                            // 将jwt存入到我们的localstore里面
                            _this.$store.commit("SET_TOKEN",jwt)
                            _this.$store.commit("SET_USERID",userId)
                            _this.$router.push("/index")
                        })
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                // this.$refs[formName].validate((valid) => {
                //     this.$router.push('/register')
                // });
                this.$router.push('/register')
            }
        }
    }
</script>

<style scoped>
    .el-header, .el-footer {
        /*background-color: #B3C0D1;*/
        color: #333;
        text-align: center;
        line-height: 60px;
    }

    .el-aside {
        background-color: #D3DCE6;
        color: #333;
        text-align: center;
        line-height: 200px;
    }

    .el-main {
        /*background-color: #E9EEF3;*/
        color: #333;
        text-align: center;
        line-height: 160px;
    }

    body > .el-container {
        margin-bottom: 40px;
    }

    .el-container:nth-child(5) .el-aside,
    .el-container:nth-child(6) .el-aside {
        line-height: 260px;
    }

    .el-container:nth-child(7) .el-aside {
        line-height: 320px;
    }

    .demo-ruleForm{
        /*max-width: 480px;*/
        /*margin: 0 auto;*/
        width: 400px;
        height: 400px;
        position: absolute;
        top: 0;
        left: 0;
        bottom: 0;
        right: 0;
        margin: auto;

    }
</style>