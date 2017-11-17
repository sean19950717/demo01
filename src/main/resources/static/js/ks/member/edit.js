/**
 * 编辑-js
 */
var vm = new Vue({
	el:'#dpLTE',
	data: {
		member: {
			id: 0
		}
	},
	methods : {
		setForm: function() {
			$.SetForm({
				url: '../../ks/member/info?_' + $.now(),
		    	param: vm.member.id,
		    	success: function(data) {
		    		vm.member = data;
		    	}
			});
		},
		acceptClick: function() {
			if (!$('#form').Validform()) {
		        return false;
		    }
		    $.ConfirmForm({
		    	url: '../../ks/member/update?_' + $.now(),
		    	param: vm.member,
		    	success: function(data) {
		    		$.currentIframe().vm.load();
		    	}
		    });
		}
	}
})