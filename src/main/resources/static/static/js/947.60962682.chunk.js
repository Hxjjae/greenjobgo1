"use strict";(self.webpackChunkgreenjobgo=self.webpackChunkgreenjobgo||[]).push([[947],{3965:(n,e,o)=>{o.d(e,{L2:()=>l,Lp:()=>t,XB:()=>r});var s=o(5294),i=o(2707);const t=s.Z.create({baseURL:"/api",headers:{"Content-Type":"application/json"}});t.interceptors.request.use((async n=>{const e=(0,i.ej)("accessToken");return e&&(console.log("\uc561\uc138\uc2a4\ud1a0\ud070",e),n.headers.Authorization="Bearer ".concat(e)),n}),(n=>(console.log(n),Promise.reject(n)))),t.interceptors.response.use((n=>n),(async n=>{const{config:e,response:o}=n,s=(0,i.ej)("refreshToken");if(console.log("\ub9ac\ud504\ub808\uc2dc\ud1a0\ud070:",s),401===o.status&&s){console.log("\ub9ac\ud504\ub808\uc2dc\ud1a0\ud070:",s);try{const{data:n}=await t.post("/sign/refresh-token",{refreshToken:s});console.log("\ub9ac\ud504\ub808\uc2dc\ud1a0\ud070:",n),console.log("\ub9ac\ud504\ub808\uc2dc\ud1a0\ud070:",s);const o=n;if((0,i.xz)("accessToken",o),console.log("\ub9ac\ud504\ub808\uc2dc\ud1a0\ud070:",s),console.log("\uc561\uc138\uc2a4\ud1a0\ud070:",o),e.headers&&e.headers.Authorization){e.headers.Authorization="Bearer ".concat(o);return await t(e)}}catch(n){console.log("\ud1a0\ud070 \uac31\uc2e0 \uc2e4\ud328:",n)}}return console.error("\uc694\uccad \uc2e4\ud328:",n),Promise.reject(n)}));const r=async(n,e,o)=>{try{const s=await t.post("/sign/sign-in",{email:n,pw:e}),{role:r,refreshToken:l,accessToken:a,vo:c,accessTokenTime:d}=await s.data;if(console.log(s.data),!(r&&l&&a))throw new Error("\uc798\ubabb\ub41c \uc751\ub2f5 \ud615\uc2dd");if((0,i.hF)("refreshToken",l),(0,i.xz)("accessToken",a),o(""),"ROLE_USER"===r)return{role:r,accessToken:a,refreshToken:l,vo:c};if("ROLE_COMPANY"===r)return{role:r,accessToken:a,refreshToken:l,vo:c,accessTokenTime:d}}catch(s){throw 432===s.response.status&&o("\uc544\uc774\ub514\ub97c \ud655\uc778 \ud574 \uc8fc\uc138\uc694"),434===s.response.status&&o("\ube44\ubc00\ubc88\ud638\ub97c \ud655\uc778 \ud574 \uc8fc\uc138\uc694"),435===s.response.status&&o("\uad8c\ud55c\uc774 \uc5c6\uc2b5\ub2c8\ub2e4. \ub2f4\ub2f9\uc790\uc5d0\uac8c \ubb38\uc758 \ubc14\ub78d\ub2c8\ub2e4."),500===s.response.status&&o("\uc11c\ubc84 \uc624\ub958 \uc785\ub2c8\ub2e4."),new Error("\ub85c\uadf8\uc778\uc5d0 \uc2e4\ud328\ud588\uc2b5\ub2c8\ub2e4.")}},l=async(n,e)=>{try{await t.post("/sign/logout");(0,i.nJ)(n),(0,i.nJ)(e)}catch(o){console.log(o)}}},3508:(n,e,o)=>{o.d(e,{Z:()=>t});o(2791);var s=o(2291),i=o(184);const t=n=>{let{header:e,open:o,close:t,onConfirm:r,children:l}=n;console.log("\uc624\ucf00\uc774 \ubaa8\ub2ec \uc791\ub3d9\ub429\ub2c8\ub2e4");return(0,i.jsx)(s.R9,{onClick:t,children:(0,i.jsx)("div",{className:o?"openConfirmModal Confimmodal":"Confirmmodal",children:o?(0,i.jsxs)("div",{className:"modalConfirm-wrapper",onClick:n=>(n=>{n.stopPropagation()})(n),children:[(0,i.jsxs)("div",{className:"modal-header",children:[e,(0,i.jsx)("p",{className:"close",onClick:t,children:(0,i.jsx)("img",{src:"".concat("","/assets/btn_menu_close.png"),alt:"X"})})]}),(0,i.jsxs)("div",{className:"modalConfirm-content",children:[l,(0,i.jsx)("div",{children:(0,i.jsx)(s.Us,{onClick:r,children:"\ud655\uc778"})})]})]}):null})})}},1947:(n,e,o)=>{o.r(e),o.d(e,{default:()=>u});var s,i,t=o(2791),r=o(7689),l=o(3965),a=o(168),c=o(7686);const d=c.Z.div(s||(s=(0,a.Z)(["\n  width: 100%;\n  height: 100vh;\n  @media all and (max-width: 767px) {\n    width: 100%;\n    height: 100vh;\n  }\n"]))),p=c.Z.ul(i||(i=(0,a.Z)(["\n  width: 100%;\n  height: 100%;\n  display: flex;\n  justify-content: space-between;\n  align-items: center;\n  li {\n    :first-of-type {\n      width: 50%;\n      height: 100vh;\n      img {\n        width: 100%;\n        height: 100vh;\n      }\n    }\n    :last-of-type {\n      display: flex;\n      justify-content: flex-end;\n      align-items: center;\n      flex-direction: column;\n      gap: 30px;\n      margin-right: 12.5%;\n      .login-title {\n        padding-left: 65px;\n      }\n      form {\n        display: flex;\n        justify-content: flex-end;\n        align-items: center;\n        flex-direction: column;\n        gap: 25px;\n        div {\n          width: 100%;\n          display: flex;\n          justify-content: flex-end;\n          align-items: center;\n          gap: 20px;\n          font-size: 16px;\n          font-weight: 600;\n          input {\n            width: 440px;\n            height: 40px;\n            border: 1px solid #aaa;\n            border-radius: 4px;\n            padding: 0 5px;\n          }\n          button {\n            width: 440px;\n            height: 50px;\n            font-size: 16px;\n            font-weight: 600;\n            color: #fff;\n            background: #228fcf;\n            border: none;\n            border-radius: 6px;\n            cursor: pointer;\n          }\n        }\n      }\n    }\n  }\n  @media all and (max-width: 767px) {\n    width: 100%;\n    height: 100%;\n    padding-top: 50%;\n    display: flex;\n    flex-direction: column;\n    justify-content: space-between;\n    align-items: center;\n    li {\n      :first-of-type {\n        display: none;\n        img {\n          display: none;\n        }\n      }\n      :last-of-type {\n        display: flex;\n        justify-content: flex-end;\n        align-items: center;\n        flex-direction: column;\n        gap: 40px;\n        margin-right: 0;\n        .login-title {\n          padding-left: 0;\n          img {\n            width: 235px;\n            height: 20px;\n          }\n        }\n        form {\n          display: flex;\n          justify-content: flex-end;\n          align-items: center;\n          flex-direction: column;\n          gap: 25px;\n          div {\n            width: 100%;\n            display: flex;\n            justify-content: flex-end;\n            align-items: center;\n            gap: 20px;\n            font-size: 16px;\n            font-weight: 600;\n            label {\n              display: none;\n            }\n            input {\n              width: 250px;\n              height: 35px;\n              border: 1px solid #aaa;\n              border-radius: 4px;\n              padding: 0 5px;\n            }\n            button {\n              width: 250px;\n              height: 40px;\n              font-size: 14px;\n              font-weight: 600;\n              color: #fff;\n              background: #228fcf;\n              border: none;\n              border-radius: 6px;\n              cursor: pointer;\n            }\n          }\n        }\n      }\n    }\n  }\n"])));var h=o(6030),g=o(4151),f=o(3508),x=o(184);const u=()=>{const[n,e]=(0,t.useState)(""),[o,s]=(0,t.useState)(""),[i,a]=(0,h.FV)(g.q),[c,u]=(0,t.useState)(!1),[m,j]=(0,t.useState)(""),w=(0,r.s0)();return(0,t.useEffect)((()=>{u(!!m)}),[m]),(0,x.jsxs)(d,{children:[(0,x.jsxs)(p,{children:[(0,x.jsx)("li",{children:(0,x.jsx)("img",{src:"../../assets/Login.png",alt:"LoginImage"})}),(0,x.jsxs)("li",{children:[(0,x.jsx)("div",{className:"login-title",children:(0,x.jsx)("img",{src:"../../assets/LoginTitle.png",alt:"LoginTitle"})}),(0,x.jsxs)("form",{children:[(0,x.jsxs)("div",{children:[(0,x.jsx)("label",{htmlFor:"login-id",children:"\uc544\uc774\ub514"}),(0,x.jsx)("input",{type:"text",id:"login-id",placeholder:"\uc544\uc774\ub514\ub97c \uc785\ub825\ud574\uc8fc\uc138\uc694.",value:n,onChange:n=>(n=>{e(n.target.value)})(n)})]}),(0,x.jsxs)("div",{children:[(0,x.jsx)("label",{htmlFor:"login-password",children:"\ube44\ubc00\ubc88\ud638"}),(0,x.jsx)("input",{type:"password",id:"login-password",placeholder:"\ube44\ubc00\ubc88\ud638\ub97c \uc785\ub825\ud574\uc8fc\uc138\uc694.",value:o,onChange:n=>(n=>{s(n.target.value)})(n)})]}),(0,x.jsx)("div",{children:(0,x.jsx)("button",{onClick:async e=>{if(e.preventDefault(),n)if(o)try{const{role:e,accessToken:s,refreshToken:i,vo:t,accessTokenTime:r}=await(0,l.XB)(n,o,j);"ROLE_USER"===e&&s?(console.log(e),a({isLogin:!0,accessToken:s,role:e,editableYn:null===t||void 0===t?void 0:t.editableYn,portfolioYn:null===t||void 0===t?void 0:t.portfolioYn,aboutMeYn:null===t||void 0===t?void 0:t.aboutMeYn}),w("/student")):"ROLE_COMPANY"===e&&s?(a({isLogin:!0,accessToken:s,role:e}),w("/business"),setTimeout((()=>{"ROLE_COMPANY"===e&&((0,l.L2)(s,i),a({isLogin:!1,accessToken:null,role:""}),w("/"))}),r),console.log(r)):w("/")}catch(s){console.log("errorrrrrrrr",s)}else j("\ube44\ubc00\ubc88\ud638\ub97c \uc785\ub825\ud574 \uc8fc\uc138\uc694.");else j("\uc544\uc774\ub514\ub97c \uc785\ub825\ud574 \uc8fc\uc138\uc694.")},children:"\ub85c\uadf8\uc778"})})]})]})]}),c&&(0,x.jsx)(f.Z,{header:"",open:c,close:()=>{u(!1),j("")},onConfirm:()=>{u(!1),j("")},children:(0,x.jsx)("span",{children:m})})]})}}}]);
//# sourceMappingURL=947.60962682.chunk.js.map