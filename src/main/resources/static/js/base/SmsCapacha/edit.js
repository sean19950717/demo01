/**
 * 编辑-js
 */
var vm = new Vue({
	el:'#dpLTE',
	data: {
		bdSmsCapatcha: {
			smsCapachaId: 0
		}
	},
	methods : {
		setForm: function() {
			$.SetForm({
				url: '../../Sys/SmsCapacha/info?_' + $.now(),
		    	param: vm.bdSmsCapatcha.smsCapachaId,
		    	success: function(data) {
		    		vm.bdSmsCapatcha = data;
		    	}
			});
		},
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.ConfirmForm({
		    	url: '../../Sys/SmsCapacha/update?_' + $.now(),
		    	param: vm.bdSmsCapatcha,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})