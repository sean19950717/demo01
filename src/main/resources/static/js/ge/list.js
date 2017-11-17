/**
 * js
 */

$(function () {
	initialPage();
	getGrid();
});

function initialPage() {
	$(window).resize(function() {
		$('#dataGrid').bootstrapTable('resetView', {height: $(window).height()-54});
	});
}

function getGrid() {
	$('#dataGrid').bootstrapTableEx({
		url: '../../ge/list?_' + $.now(),
		height: $(window).height()-54,
		queryParams: function(params){
			params.name = vm.keyword;
			return params;
		},
		columns: [
			{checkbox: true},
			{field : "aaBb", title : "aaa", width : "100px"}, 
			{field : "ccDd", title : "bbb", width : "100px"}
		],
        responseHandler:function(res) {
            //数据端数据自定义封装，所以需要映射到对应字段中
            res.total=0;
            if(res.code == 200){
                res.rows=res.data.rows;
                res.total=res.data.total;
            }
            console.log("列表返回结果："+JSON.stringify(res));
            return res;
        },
        onLoadSuccess:function(result){
            //TODO:该方法需要进行整体封装
            if(result.code !== 200){
                alert(result.msg);
            }
        },
        onLoadError:function () {
            alert("服务器错误，请联系管理员！");
        }
	})
}

var vm = new Vue({
	el:'#dpLTE',
	data: {
		keyword: null
	},
	methods : {
		load: function() {
			$('#dataGrid').bootstrapTable('refresh');
		},
		save: function() {
			dialogOpen({
				title: '新增',
				url: 'ge/add.html?_' + $.now(),
				width: '420px',
				height: '350px',
				yes : function(iframeId) {
					top.frames[iframeId].vm.acceptClick();
				},
			});
		},
		edit: function() {
			var ck = $('#dataGrid').bootstrapTable('getSelections');
			if(checkedRow(ck)){
				dialogOpen({
					title: '编辑',
					url: 'ge/edit.html?_' + $.now(),
					width: '420px',
					height: '350px',
					success: function(iframeId){
						top.frames[iframeId].vm.geTest.aaBb = ck[0].aaBb;
						top.frames[iframeId].vm.setForm();
					},
					yes: function(iframeId){
						top.frames[iframeId].vm.acceptClick();
					}
				});
			}
		},
		remove: function() {
			var ck = $('#dataGrid').bootstrapTable('getSelections'), ids = [];	
			if(checkedArray(ck)){
				$.each(ck, function(idx, item){
					ids[idx] = item.aaBb;
				});
				$.RemoveForm({
					url: '../../ge/remove?_' + $.now(),
			    	param: ids,
			    	success: function(data) {
			    		vm.load();
			    	}
				});
			}
		}
	}
})