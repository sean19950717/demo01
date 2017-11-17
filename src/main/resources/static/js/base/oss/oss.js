$(function () {
    initialPage();
    getGrid();
    upload();
});
function initialPage() {
    $(window).resize(function() {
        $('#dataGrid').bootstrapTable('resetView', {height: $(window).height()-54});
    });
}
function upload(){
        new AjaxUpload('#upload', {
            action: '../../sys/oss/upload',
            name: 'filename',
            autoSubmit: true,
            data:{
                "token":localStorage.getItem("token"),
            },
            responseType: "json",
            onSubmit: function (file, extension) {
                if (vm.config.type == null) {
                    alert("云存储配置未配置");
                    return false;
                }
                if (!(extension && /^(jpg|jpeg|png|gif|avi)$/.test(extension.toLowerCase()))) {
                    alert('只支持jpg、png、gif格式的图片！');
                    return false;
                }
            },
            onComplete: function (file, r) {
                if (r.code == 0) {
                   dialogMsg("上传成功","success");
                    vm.load();
                } else {
                    alert(r.msg);
                }
            }
        })
}
function getGrid() {
    $('#dataGrid').bootstrapTableEx({
        url: '../../sys/oss/list',
        height: $(window).height()-54,
        queryParams: function(params){
            return params;
        },
        columns: [{
            checkbox: true
        }, {
            field : "id",
            title : "编号",
            width : "50px"
        }, {
            field : "url",
            title : "网址",
            width : "200px"
        }, {
            field : "createDate",
            title : "创建时间",
            width : "200px"
        },{
            field : "url",
            title : "网址",
            width : "100px",
            formatter:function (value,row,index) {
            //return '<a href="'+value+'">视频下载</a>'
                return '<a href="'+value+'" class="btn btn-link">视频下载</a>';
    }
        }]
    })
}
var vm = new Vue({
    el:'#rrapp',
    data:{
        showList: true,
        title: null,
        config: {}
    },
    created: function(){
        this.getConfig();
    },
    methods: {
        load:function () {
            $("#dataGrid").bootstrapTable("refresh");
        },
        getConfig: function () {
            $.getJSON("../../" + "sys/oss/config", function(r){
                vm.config = r.config;
            });
        },
        addConfig: function(){
            vm.showList = false;
            vm.title = "云存储配置";
        },
        saveOrUpdate: function () {
            var url = "../../" + "sys/oss/saveConfig";
            $.ajax({
                type: "POST",
                url: url,
                contentType: "application/json",
                data: JSON.stringify(vm.config),
                success: function(r){
                    if(r.code === 0){
                        dialogMsg("操作成功","success");
                        /*alert('操作成功', function(){
                            vm.reload();
                        });*/
                    }else{
                        dialogMsg("操作失败","error");
                       /* alert(r.msg);*/
                    }
                }
            });
        },
        deleteById: function() {
            var ck = $('#dataGrid').bootstrapTable('getSelections'), ids = [];
            if(checkedArray(ck)){
                $.each(ck, function(idx, item){
                    ids[idx] = item.id;
                });
                $.RemoveForm({
                    url: '../../sys/oss/delete?_' + $.now(),
                    param: ids,
                    success: function(data) {
                        dialogMsg("操作成功","success");
                        vm.load();
                    }
                });
            }
        },
    }
});