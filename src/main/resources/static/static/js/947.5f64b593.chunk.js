"use strict";(self.webpackChunkgreenjobgo=self.webpackChunkgreenjobgo||[]).push([[947],{3965:(n,e,i)=>{i.d(e,{L2:()=>a,XB:()=>r,ZP:()=>d});var s=i(5294),t=i(2707);const o=s.Z.create({baseURL:"/api",withXSRFToken:!0,headers:{"Content-Type":"application/json"}});o.interceptors.request.use((async n=>{const e=(0,t.ej)("accessToken");return e&&(n.headers.Authorization="Bearer ".concat(e),n.headers||(n.headers={})),n}),(n=>Promise.reject(n))),o.interceptors.response.use((n=>n),(async n=>{const{config:e,response:i}=n,s=(0,t.ej)("refreshToken");if(401===(null===i||void 0===i?void 0:i.status)&&s)try{var r;const{data:n}=await o.post("/sign/refresh-token",{refreshToken:s}),i=n;if((0,t.d8)("accessToken",i),null!==e&&void 0!==e&&e.headers&&null!==(r=e.headers)&&void 0!==r&&r.Authorization){e.headers.Authorization="Bearer ".concat(i);return await o(e)}}catch(n){console.log("\ud1a0\ud070 \uac31\uc2e0 \uc2e4\ud328:",n)}return console.error("\uc694\uccad \uc2e4\ud328:",n),Promise.reject(n)}));const r=async(n,e,i)=>{try{const s=await o.post("/sign/sign-in",{email:n,pw:e}),{role:r,refreshToken:a,accessToken:l,vo:d,accessTokenTime:c}=await s.data;if(r&&a&&l)return(0,t.d8)("refreshToken",a),(0,t.d8)("accessToken",l),i(""),{role:r,accessToken:l,refreshToken:a,vo:d,accessTokenTime:c};throw new Error("\uc798\ubabb\ub41c \uc751\ub2f5 \ud615\uc2dd")}catch(s){l(s,i)}},a=async()=>{try{await o.post("/sign/logout"),(0,t.nJ)("accessToken"),(0,t.nJ)("refreshToken"),delete o.defaults.headers.common.Authorization}catch(n){console.error("\ub85c\uadf8\uc544\uc6c3 \uc2e4\ud328:",n)}},l=(n,e)=>{throw 432===n.response.status?e("\uc544\uc774\ub514\ub97c \ud655\uc778 \ud574 \uc8fc\uc138\uc694"):434===n.response.status?e("\ube44\ubc00\ubc88\ud638\ub97c \ud655\uc778 \ud574 \uc8fc\uc138\uc694"):435===n.response.status?e("\uad8c\ud55c\uc774 \uc5c6\uc2b5\ub2c8\ub2e4. \ub2f4\ub2f9\uc790\uc5d0\uac8c \ubb38\uc758 \ubc14\ub78d\ub2c8\ub2e4."):500===n.response.status&&e("\uc11c\ubc84 \uc624\ub958 \uc785\ub2c8\ub2e4."),new Error("\ub85c\uadf8\uc778\uc5d0 \uc2e4\ud328\ud588\uc2b5\ub2c8\ub2e4.")},d=o},3508:(n,e,i)=>{i.d(e,{Z:()=>o});i(2791);var s=i(2291),t=i(184);const o=n=>{let{header:e,open:i,close:o,onConfirm:r,children:a}=n;console.log("\uc624\ucf00\uc774 \ubaa8\ub2ec \uc791\ub3d9\ub429\ub2c8\ub2e4");return(0,t.jsx)(s.R9,{onClick:o,children:(0,t.jsx)("div",{className:i?"openConfirmModal Confimmodal":"Confirmmodal",children:i?(0,t.jsxs)("div",{className:"modalConfirm-wrapper",onClick:n=>(n=>{n.stopPropagation()})(n),children:[(0,t.jsxs)("div",{className:"modal-header",children:[e,(0,t.jsx)("p",{className:"close",onClick:o,children:(0,t.jsx)("img",{src:"".concat("","/assets/btn_menu_close.png"),alt:"X"})})]}),(0,t.jsxs)("div",{className:"modalConfirm-content",children:[a,(0,t.jsx)("div",{children:(0,t.jsx)(s.Us,{onClick:r,children:"\ud655\uc778"})})]})]}):null})})}},1947:(n,e,i)=>{i.r(e),i.d(e,{default:()=>u});var s,t,o=i(2791),r=i(7689),a=i(3965),l=i(168),d=i(7686);const c=d.Z.div(s||(s=(0,l.Z)(["\n  width: 100%;\n  height: 100vh;\n  @media all and (max-width: 767px) {\n    width: 100%;\n    height: 100vh;\n  }\n"]))),h=d.Z.ul(t||(t=(0,l.Z)(["\n  width: 100%;\n  height: 100%;\n  display: flex;\n  justify-content: space-between;\n  align-items: center;\n  li {\n    :first-of-type {\n      width: 50%;\n      height: 100vh;\n      img {\n        width: 100%;\n        height: 100vh;\n      }\n    }\n    :last-of-type {\n      display: flex;\n      justify-content: flex-end;\n      align-items: center;\n      flex-direction: column;\n      gap: 30px;\n      margin-right: 12.5%;\n      .login-title {\n        padding-left: 65px;\n      }\n      form {\n        display: flex;\n        justify-content: flex-end;\n        align-items: center;\n        flex-direction: column;\n        gap: 25px;\n        div {\n          width: 100%;\n          display: flex;\n          justify-content: flex-end;\n          align-items: center;\n          gap: 20px;\n          font-size: 16px;\n          font-weight: 600;\n          input {\n            width: 440px;\n            height: 40px;\n            border: 1px solid #aaa;\n            border-radius: 4px;\n            padding: 0 5px;\n          }\n          button {\n            width: 440px;\n            height: 50px;\n            font-size: 16px;\n            font-weight: 600;\n            color: #fff;\n            background: #228fcf;\n            border: none;\n            border-radius: 6px;\n            cursor: pointer;\n          }\n        }\n      }\n    }\n  }\n  @media all and (max-width: 767px) {\n    width: 100%;\n    height: 100%;\n    padding-top: 50%;\n    display: flex;\n    flex-direction: column;\n    justify-content: space-between;\n    align-items: center;\n    li {\n      :first-of-type {\n        display: none;\n        img {\n          display: none;\n        }\n      }\n      :last-of-type {\n        display: flex;\n        justify-content: flex-end;\n        align-items: center;\n        flex-direction: column;\n        gap: 40px;\n        margin-right: 0;\n        .login-title {\n          padding-left: 0;\n          img {\n            width: 235px;\n            height: 20px;\n          }\n        }\n        form {\n          display: flex;\n          justify-content: flex-end;\n          align-items: center;\n          flex-direction: column;\n          gap: 25px;\n          div {\n            width: 100%;\n            display: flex;\n            justify-content: flex-end;\n            align-items: center;\n            gap: 20px;\n            font-size: 16px;\n            font-weight: 600;\n            label {\n              display: none;\n            }\n            input {\n              width: 250px;\n              height: 35px;\n              border: 1px solid #aaa;\n              border-radius: 4px;\n              padding: 0 5px;\n            }\n            button {\n              width: 250px;\n              height: 40px;\n              font-size: 14px;\n              font-weight: 600;\n              color: #fff;\n              background: #228fcf;\n              border: none;\n              border-radius: 6px;\n              cursor: pointer;\n            }\n          }\n        }\n      }\n    }\n  }\n"])));var p=i(6030),f=i(4151),g=i(3508),x=(i(2707),i(184));const u=()=>{const[n,e]=(0,o.useState)(""),[i,s]=(0,o.useState)(""),[t,l]=(0,p.FV)(f.q),[d,u]=(0,o.useState)(!1),[m,j]=(0,o.useState)(""),v=(0,r.s0)();return(0,o.useEffect)((()=>{u(!!m)}),[m]),(0,x.jsxs)(c,{children:[(0,x.jsxs)(h,{children:[(0,x.jsx)("li",{children:(0,x.jsx)("img",{src:"../../assets/Login.png",alt:"LoginImage"})}),(0,x.jsxs)("li",{children:[(0,x.jsx)("div",{className:"login-title",children:(0,x.jsx)("img",{src:"../../assets/LoginTitle.png",alt:"LoginTitle"})}),(0,x.jsxs)("form",{children:[(0,x.jsxs)("div",{children:[(0,x.jsx)("label",{htmlFor:"login-id",children:"\uc544\uc774\ub514"}),(0,x.jsx)("input",{type:"text",id:"login-id",placeholder:"\uc544\uc774\ub514\ub97c \uc785\ub825\ud574\uc8fc\uc138\uc694.",value:n,onChange:n=>(n=>{e(n.target.value)})(n)})]}),(0,x.jsxs)("div",{children:[(0,x.jsx)("label",{htmlFor:"login-password",children:"\ube44\ubc00\ubc88\ud638"}),(0,x.jsx)("input",{type:"password",id:"login-password",placeholder:"\ube44\ubc00\ubc88\ud638\ub97c \uc785\ub825\ud574\uc8fc\uc138\uc694.",value:i,onChange:n=>(n=>{s(n.target.value)})(n)})]}),(0,x.jsx)("div",{children:(0,x.jsx)("button",{onClick:async e=>{if(e.preventDefault(),n)if(i)try{const{role:e,accessToken:s,refreshToken:t,vo:o,accessTokenTime:r}=await(0,a.XB)(n,i,j);"ROLE_USER"===e&&s?(console.log(e),l({isLogin:!0,role:e,editableYn:null===o||void 0===o?void 0:o.editableYn,portfolioYn:null===o||void 0===o?void 0:o.portfolioYn,aboutMeYn:null===o||void 0===o?void 0:o.aboutMeYn}),v("/student")):"ROLE_COMPANY"===e&&s?(l({isLogin:!0,role:e}),v("/business"),setTimeout((()=>{"ROLE_COMPANY"===e&&((0,a.L2)(s,t),l({isLogin:!1,accessToken:null,role:""}),v("/"))}),r),console.log(r)):v("/")}catch(s){console.log("errorrrrrrrr",s)}else j("\ube44\ubc00\ubc88\ud638\ub97c \uc785\ub825\ud574 \uc8fc\uc138\uc694.");else j("\uc544\uc774\ub514\ub97c \uc785\ub825\ud574 \uc8fc\uc138\uc694.")},children:"\ub85c\uadf8\uc778"})})]})]})]}),d&&(0,x.jsx)(g.Z,{header:"",open:d,close:()=>{u(!1),j("")},onConfirm:()=>{u(!1),j("")},children:(0,x.jsx)("span",{children:m})})]})}}}]);
//# sourceMappingURL=947.5f64b593.chunk.js.map