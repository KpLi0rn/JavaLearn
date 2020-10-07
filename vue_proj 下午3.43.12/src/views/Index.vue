<template>
    <div>
        <el-container>
            <el-main>
                <el-form ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item>
                        <el-page-header @back="goBack" content="主页">
                        </el-page-header>
<!--                        <el-button id="test" type="primary" @click="submitForm()">退出登录</el-button>-->
                        <el-calendar v-model="value">
                        </el-calendar>

                    </el-form-item>
                </el-form>
            </el-main>
        </el-container>
    </div>
</template>

<script>
    export default {
        name: "Index.vue",
        methods: {
            // submitForm() {
            //     this.$store.commit("REMOVE_INFO")
            //     this.$axios.get("/logout").then(res =>{
            //         _this.$router.push("/login")
            //     })
            // },
            goBack() {
                // console.log('go back');
                this.$store.commit("REMOVE_INFO")
                this.$axios.get("/logout").then(res =>{
                    _this.$router.push("/login")
                })
            }
        },
        data() {
            return {
                value: new Date()
            }
        }
    }

</script>

<style scoped>
    .el-header, .el-footer {
        background-color: #B3C0D1;
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
</style>