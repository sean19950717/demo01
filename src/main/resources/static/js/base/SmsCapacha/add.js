/**
 * 新增-js
 */
var vm = new Vue({
	el:'#dpLTE',
	data: {
		bdSmsCapatcha: {
			smsCapachaId: 0
		}
	},
	methods : {
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.SaveForm({
		    	url: '../../Sys/SmsCapacha/save?_' + $.now(),
		    	param: vm.bdSmsCapatcha,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})
