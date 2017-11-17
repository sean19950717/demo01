/**
 * 新增-js
 */
var vm = new Vue({
	el:'#dpLTE',
	data: {
		test: {
			id: 0
		}
	},
	methods : {
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.SaveForm({
		    	url: '../../test/save?_' + $.now(),
		    	param: vm.test,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})
