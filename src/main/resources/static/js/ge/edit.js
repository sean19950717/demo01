/**
 * 编辑-js
 */
var vm = new Vue({
	el:'#dpLTE',
	data: {
		geTest: {
			aaBb: 0
		}
	},
	methods : {
		setForm: function() {
			$.SetForm({
				url: '../../ge/info?_' + $.now(),
		    	param: vm.geTest.aaBb,
		    	success: function(data) {
		    		vm.geTest = data;
		    	}
			});
		},
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.ConfirmForm({
		    	url: '../../ge/update?_' + $.now(),
		    	param: vm.geTest,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})