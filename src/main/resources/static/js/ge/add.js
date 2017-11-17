/**
 * 新增-js
 */
var vm = new Vue({
	el:'#dpLTE',
	data: {
		geTest: {
			aaBb: 0
		}
	},
	methods : {
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.SaveForm({
		    	url: '../../ge/save?_' + $.now(),
		    	param: vm.geTest,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})
