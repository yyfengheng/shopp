<template>
    <div class="productMerchantBase">
        <base-layout class="baseLayout" v-bind:activeIndex="3"></base-layout>
        <div class="productMerchantBody">
            <div class="header">
                <span style="font-size: 20px;">商品管理</span>
                <button @click="dialog.addDialogVisible = true">增加</button>
            </div>

            <!-- 窗口 -->
            <!-- 新增商品窗口 -->
            <el-dialog title="添加商品" :visible.sync="dialog.addDialogVisible" width="400px" :before-close="handleClose">
                <el-form :model="addForm" label-position="right" label-width="100px" size="mini">
                    <el-form-item label="商品名称" >
                        <el-col :span="20">
                            <el-input v-model="addForm.name" autocomplete="on" type="textarea"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="商品单价">
                        <el-col :span="20">
                            <el-input v-model="addForm.price" autocomplete="on"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="商品数量" width="50px">
                        <el-col :span="5">
                            <el-input-number v-model="addForm.quantity" :min="1" :max="100" label="描述文字"></el-input-number>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="商品分类" width="50px">
                        <el-col :span="20">
                            <el-select v-model="addForm.categoryId" placeholder="请选择分类">
                                <el-option v-for="item in category" :key="item.id" :label="item.name" :value="item.id"></el-option>
                            </el-select>
                        </el-col>
                    </el-form-item><el-form-item label="商品状态" width="50px">
                        <el-col :span="5">
                            <el-switch
                            v-model="addForm.status"
                            active-color="#13ce66"
                            inactive-color="#ff4949">
                            </el-switch>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="商品图片" width="50px">
                        <el-col :span="5">
                            <img v-if="echoImg != ''" class="img" :src="echoImg" alt=""><input type="file" @change="uploadImage">
                        </el-col>
                    </el-form-item>
                    
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialog.addDialogVisible = false">取 消</el-button>
                    <el-button type="primary" @click="addProduct()">保 存</el-button>
                </div>
            </el-dialog>
            <!-- 新增商品窗口 -->

            <!-- 修改商品窗口 -->
            <el-dialog title="修改商品" :visible.sync="dialog.updateDialogVisible" width="400px" :before-close="handleClose">
                <el-form :model="updateForm" label-position="right" label-width="100px" size="mini">
                    <el-form-item label="商品ID" >
                        <el-col :span="3">
                            {{updateForm.id}}
                        </el-col>
                    </el-form-item>
                    <el-form-item label="商品名称" >
                        <el-col :span="20">
                            <el-input v-model="updateForm.name" autocomplete="on" type="textarea"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="商品单价">
                        <el-col :span="20">
                            <el-input v-model="updateForm.price" autocomplete="on"></el-input>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="商品数量" width="50px">
                        <el-col :span="5">
                            <el-input-number v-model="updateForm.quantity" :min="1" :max="100" label="描述文字"></el-input-number>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="商品分类" width="50px">
                        <el-col :span="20">
                            <el-select v-model="updateForm.categoryId"  placeholder="请选择分类">
                                <el-option v-for="item in category" :key="item.id" :label="item.name" :value="item.id"></el-option>
                            </el-select>
                        </el-col>
                    </el-form-item><el-form-item label="商品状态" width="50px">
                        <el-col :span="5">
                            <el-switch
                            v-model="updateForm.status"
                            active-color="#13ce66"
                            inactive-color="#ff4949">
                            </el-switch>
                        </el-col>
                    </el-form-item>
                    <el-form-item label="商品图片" width="50px">
                        <el-col :span="5">
                            <img v-if="echoImg != ''" class="img" :src="echoImg" alt=""><input type="file" @change="uploadImage">
                        </el-col>
                    </el-form-item>
                    
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button @click="dialog.updateDialogVisible = false">取 消</el-button>
                    <el-button type="primary" @click="updateProduct()">保 存</el-button>
                </div>
            </el-dialog>
            <!-- 修改商品信息窗口 -->

            <hr>
            <div class="child-productMerchant">
                <el-table
                    :data="tableData.data.records"
                    border
                    style="width: 100%" >
                    <el-table-column
                    prop="id"
                    align="center"
                    label="商品ID">
                    </el-table-column>
                    <el-table-column
                    prop="name"
                    align="center"
                    label="商品名称">
                    </el-table-column>
                    <el-table-column
                    prop="image"
                    align="center"
                    label="图片">
                        <template slot-scope="scope" >
                            <el-image
                            style="width: 100px; height: 150px"
                            :src="scope.row.image"
                            fit="fill"></el-image>
                        </template>
                    </el-table-column>
                    <el-table-column
                    prop="categoryId"
                    align="center"
                    label="类别">
                        <template slot-scope="scope">
                            <span>{{ findNameById(scope.row) }}</span>
                        </template>
                    </el-table-column>
                    <el-table-column
                    prop="price"
                    align="center"
                    label="价格">
                    </el-table-column>
                    <el-table-column
                    prop="quantity"
                    align="center"
                    label="商品数量">
                    </el-table-column>
                    <el-table-column
                    prop="status"
                    align="center"
                    label="状态">
                        <template scope="scope">
                            <el-switch
                                active-color="#5B7BFA"
                                inactive-color="#dadde5"
                                v-model="scope.row.status" 
                                @change=change(scope.$index,scope.row)                
                            >
                            </el-switch>
                        </template>
                    </el-table-column>
                    <el-table-column align="center" label="操作" width="150px">
                        <template slot-scope="scope">
                            <el-button
                            size="mini"
                            @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                            <el-button
                            size="mini"
                            type="danger"
                            @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                        </template>
                    </el-table-column>
                </el-table>
                <div class="page">
                <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="page"
                    :page-sizes="pageSizes"
                    :page-size="pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total">
                </el-pagination>
            </div>
            </div>
            
        </div>
    </div>
</template>

<script>

import BaseLayout from '@/components/BaseLayout.vue'

export default {
    name:"ProductMerchantView",
    components:{
        BaseLayout
    },
    data:function(){
        return {

            // 分页组件参数 
            total:40, //总数
            pageSize:10, //每页记录数
            pageSizes:[10, 20, 30, 40], 
            page: 1,//第几页,页码

            //商品管理表数据
            tableData:{
                "code": "200",
                "msg": "success",
                "data": {
                    "total": 1,
                    "records":[
                        {
                            id:1,
                            name:"",
                            image:"",
                            categoryId:0,
                            price:0.0,
                            quantity:0,
                            status:true
                        },
                        {
                            id:2,
                            name:"",
                            image:"",
                            categoryId:0,
                            price:0.0,
                            quantity:0,
                            status:true
                        }
                    ]
                }
            },
            
            // dialog对话框参数
            dialog:{
                // 新增商品dialog
                addDialogVisible:false,
                updateDialogVisible:false
            },

            // 新增表单
            addForm: {
                name: '',
                price: 0.0,
                quantity: 0,
                categoryId: '',
                status: true,
                image: ''
            },

            // 修改表单
            updateForm: {
                id:1,
                name: '',
                price: 0.0,
                quantity: 0,
                categoryId: '',
                status: true,
                image: ''
            },

            // 传入后端商品信息类
            productDTO:{
                id:0,
                name: '',
                price: 0.0,
                quantity: 0,
                categoryId: 0,
                status: 1,
                image: ''
            },

            // 类别
            category:[
                {
                    id:1,
                    name:"666",
                    status:1
                },
                {
                    id:2,
                    name:"777",
                    status:1
                }
            ],
            categoryId: '',

            // 上传后,回显的图片地址
            echoImg:'',
        }
    },
    methods: {

        /* 前端设置数据 */
        //设置每页记录数
        handleSizeChange(val) {
            console.log("每条记录数"+val);
            console.log("页码"+this.page);
            this.pageSize = val;
            this.getByUserId(null,this.page,this.pageSize);
            console.log(this.pageSize)
        },
        //设置页码
        handleCurrentChange(val) {
            console.log("页码"+val);
            console.log("每页记录数"+this.pageSize);
            this.page = val;
            this.getByUserId(null,this.page,this.pageSize);
            console.log(this.page)
        },
        // 监听关闭dialog窗口调用方法
        handleClose(done){
            console.log("无敌")
            this.echoImg = ""
            done();
        },
        // 打开修改商品信息
        handleEdit(index, row) {
            console.log(row);
            this.getByProductId(row.id);
            this.dialog.updateDialogVisible = true;
        },
        // 删除商品信息
        handleDelete(index, row) {
            console.log(row);
            this.deleteProduct(row.id);
            window.location.reload()
        },
        // 修改商品状态
        change:function(index,row){
            console.log(row);
            this.productDTO = row
            if(this.productDTO.status == true){
                this.productDTO.status = 1
            }else{
                this.productDTO.status = 2
            }
            console.log(this.productDTO)
            this.editProduct(this.productDTO)
            window.location.reload()
        },
        // 新增商品
        addProduct(){
            this.productDTO = this.addForm
            if(this.addForm.status == true){
                this.productDTO.status = 1
            }else{
                this.productDTO.status = 2
            }
            this.productDTO.image = this.echoImg
            console.log(this.productDTO)
            this.insertProduct(this.productDTO)
            window.location.reload()
            this.dialog.addDialogVisible = false;
        },
        // 修改商品
        updateProduct(){
            this.productDTO = this.updateForm;
            this.productDTO.image = this.echoImg;
            if(this.productDTO.status == true){
                this.productDTO.status = 1
            }else{
                this.productDTO.status = 2
            }
            console.log(this.productDTO)
            this.editProduct(this.productDTO)
            window.location.reload()
            this.dialog.updateDialogVisible = false;
        },
        // 上传图片
        uploadImage(event) {
            console.log(666);
            const formData = new FormData();
            const imageFile = event.target.files[0];
            formData.append('file', imageFile);
            
            let url = this.$httpAddress + this.$uploadAddress;

            this.$axios.post(url,formData,{
                headers:{
                    'Content-Type': 'multipart/form-data'
                }
            })
            .then(response=>{
                console.log(response)
                if(response.data.msg=="success"){
                    this.echoImg = response.data.data
                    console.log(this.echoImg)
                }else{
                    alert(response.data.msg)
                }
                
            })
            .catch(error=>{
                console.log(error)
            })
        },

        /* 根据分类id查询分类name */
        findNameById(row) {
            console.log(row);

            let obj = this.category;
            let id = row.categoryId;

            for (let o of obj) {
                if (o.id == id) {
                    return o.name;
                }
            }
            return id;
        },

        /* 访问后端的方法 */
        /* 根据用户ID查询商品信息 */
        getByUserId(userId,page,pageSize){
            let url = this.$httpAddress + this.$productAddress + "/merchant";
            this.$axios.get(url,{
                params: {
                    page: page,
                    pageSize: pageSize
                }
            })
            .then(response=>{
                if(response.data.msg=="success"){
                    this.tableData = response.data
                    let records = this.tableData.data.records
                    for(const i of records){
                        console.log(i.status)
                        if(i.status == 1){
                            i.status = true;
                        }else{
                            i.status = false;
                        } 
                    }
                    this.total = this.tableData.data.total
                }else{
                    alert(response.data.msg)
                }
                
            })
            .catch(error=>{
                console.log(error)
            })
        },
        /* 根据商品ID查询商品信息 */
        getByProductId(id){
            let url = this.$httpAddress + this.$productAddress + "/" + id;
            this.$axios.get(url)
            .then(response=>{
                console.log(response.data)
                if(response.data.msg=="success"){
                    this.updateForm = response.data.data
                    if(this.updateForm.status == 1){
                        this.updateForm.status = true
                    }else{
                        this.updateForm.status = false
                    }
                }else{
                    alert(response.data.msg)
                }
                console.log(this.updateForm)
            })
            .catch(error=>{
                console.log(error)
            })
        },
        /* 查询商品类别 */
        queryAllCategory(){
            let url = this.$httpAddress + this.$categoryAddress
            this.$axios.get(url)
            .then(response=>{
                console.log(response)
                if(response.data.msg == "success"){
                    this.category = response.data.data
                }else{
                    // alert(response.data.msg);
                }
            })
            .catch(error=>{
                console.log(error)
            })
        },

        /* 新增商品信息 */
        insertProduct(productDTO){
            let url = this.$httpAddress + this.$productAddress + "/merchant"

            this.$axios.post(url,JSON.stringify(productDTO),{
                headers:{
                    'Content-Type': 'application/json'
                }
            })
            .then(response=>{
                alert(response.data.msg)
                if(response.data.msg=="success"){
                    alert("新增商品成功")
                }else{
                    alert(response.data.msg)
                }
            })
            .catch(error=>{
                console.log(error)
            })
        },
        /* 根据id修改商品信息 */
        editProduct(productDTO){
            let url = this.$httpAddress + this.$productAddress + "/merchant";

            this.$axios.put(url,JSON.stringify(productDTO),{
                headers:{
                    "Content-Type": "application/json"
                }
            })
            .then(response=>{
                if(response.data.msg == "success"){
                    alert("修改成功");
                }else{
                    alert(response.data.msg);
                }
            })
            .catch(error=>{
                console.log(error)
            })
        },
        /* 根据id删除商品信息 */
        deleteProduct(id){
            let url = this.$httpAddress + this.$productAddress + "/merchant";
            let array = [];
            array.push(id);
            // 将数组转为查询参数
            let queryParams = array.map(function(item) {
                return 'ids=' + encodeURIComponent(item);
            }).join('&');
            console.log(queryParams)
            this.$axios.delete(url+"?"+queryParams)
            .then(response=>{
                if(response.data.msg == "success"){
                    alert("删除成功")
                }else{
                    alert(response.data.msg)
                }
            })
            .catch(error=>{
                console.log(error)
            })
        }
    },

    // 挂载完成自动执行
    mounted(){
        this.getByUserId(null,this.page,this.pageSize);
        this.queryAllCategory();
    }

}
</script>

<style>
    .productMerchantBase{
        width: 1380px;
        height: 540px;
        margin: 0 auto;
        margin-top: 30px;
        position: relative;
    }
    /* 设置基础布局组件 */
    .baseLayout {
        position: absolute;
        margin-top: -30px;
        top: 0; /* 距离容器顶部的距离 */
        left: 0; /* 距离容器左侧的距离 */
    }

    /* 设置主体内容 */
    .productMerchantBody {
        position: absolute;
        top: 0; /* 距离容器顶部的距离 */
        left: 180px; /* 距离容器左侧的距离 */
        width: 1200px;
        height: 620px;
        overflow-y: auto;
        text-align: center;
        font-weight: 1000;
    }
    .productMerchantBody>hr{
        width: 950px;
    }
    /* 主体内容中的头部 */
    .header{
        width: 945px;
        height: 40px;
        margin: 0 auto;
        margin-top: 20px;
        display: flex;
        justify-content: space-between;
        align-items: flex-end;
    }
    .header>button{
        width: 70px;
        background-color: #769CF2;
        color: white;
        border-radius: 4px;
        border: 1px solid #BBBBBB;
    }

    /* 商品管理信息 */
    .child-productMerchant{
        width: 950px;
        height: 620px;
        margin: 0 auto;
    }

    /* 上传图片回显 */
    .img{
        width: 160px;
        height: 210px;
    }

    /* 分页 */
    .page{
        width: 950px;
        height: 40px;
        line-height: 40px;
        text-align: right;
        margin: 0 auto;
    }

</style>