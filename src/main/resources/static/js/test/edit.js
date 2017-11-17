/**
 * 编辑-js
 */
var vm = new Vue({
	el:'#dpLTE',
	data: {
		test: {
			id: 0
		}
	},
	methods : {
		setForm: function() {
			$.SetForm({
				url: '../../test/info?_' + $.now(),
		    	param: vm.test.id,
		    	success: function(data) {
		    		vm.test = data;
		    	}
			});
		},
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.ConfirmForm({
		    	url: '../../test/update?_' + $.now(),
		    	param: vm.test,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})