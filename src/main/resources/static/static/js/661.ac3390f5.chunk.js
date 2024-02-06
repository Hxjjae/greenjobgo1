"use strict";(self.webpackChunkgreenjobgo=self.webpackChunkgreenjobgo||[]).push([[661],{3844:(n,e,i)=>{i.d(e,{L2:()=>a,XB:()=>r,ZP:()=>d});var t=i(5662),s=i(4120);const o=t.Z.create({baseURL:"/api",withXSRFToken:!0,headers:{"Content-Type":"application/json"}});o.interceptors.request.use((async n=>{const e=(0,s.ej)("accessToken");return e&&(n.headers.Authorization="Bearer ".concat(e),n.headers||(n.headers={})),n}),(n=>Promise.reject(n))),o.interceptors.response.use((n=>n),(async n=>{const{config:e,response:i}=n,t=(0,s.ej)("refreshToken");if(401===(null===i||void 0===i?void 0:i.status)&&t)try{var r;const{data:n}=await o.post("/sign/refresh-token",{refreshToken:t}),i=n;if((0,s.d8)("accessToken",i),null!==e&&void 0!==e&&e.headers&&null!==(r=e.headers)&&void 0!==r&&r.Authorization){e.headers.Authorization="Bearer ".concat(i);return await o(e)}}catch(n){}return console.error("\uc694\uccad \uc2e4\ud328:",n),Promise.reject(n)}));const r=async(n,e,i)=>{try{const t=await o.post("/sign/sign-in",{email:n,pw:e}),{role:r,refreshToken:a,accessToken:l,vo:d,accessTokenTime:c}=await t.data;if(r&&a&&l)return(0,s.d8)("refreshToken",a),(0,s.d8)("accessToken",l),i(""),{role:r,accessToken:l,refreshToken:a,vo:d,accessTokenTime:c};throw new Error("\uc798\ubabb\ub41c \uc751\ub2f5 \ud615\uc2dd")}catch(t){l(t,i)}},a=async()=>{try{await o.post("/sign/logout"),(0,s.nJ)("accessToken"),(0,s.nJ)("refreshToken"),delete o.defaults.headers.common.Authorization}catch(n){console.error("\ub85c\uadf8\uc544\uc6c3 \uc2e4\ud328:",n)}},l=(n,e)=>{throw 432===n.response.status?e("\uc544\uc774\ub514\ub97c \ud655\uc778 \ud574 \uc8fc\uc138\uc694"):434===n.response.status?e("\ube44\ubc00\ubc88\ud638\ub97c \ud655\uc778 \ud574 \uc8fc\uc138\uc694"):435===n.response.status?e("\uad8c\ud55c\uc774 \uc5c6\uc2b5\ub2c8\ub2e4. \ub2f4\ub2f9\uc790\uc5d0\uac8c \ubb38\uc758 \ubc14\ub78d\ub2c8\ub2e4."):500===n.response.status&&e("\uc11c\ubc84 \uc624\ub958 \uc785\ub2c8\ub2e4."),new Error("\ub85c\uadf8\uc778\uc5d0 \uc2e4\ud328\ud588\uc2b5\ub2c8\ub2e4.")},d=o},7307:(n,e,i)=>{i.d(e,{Z:()=>o});i(7313);var t=i(3607),s=i(6417);const o=n=>{let{header:e,open:i,close:o,onConfirm:r,children:a}=n;return(0,s.jsx)(t.R9,{onClick:o,children:(0,s.jsx)("div",{className:i?"openConfirmModal Confimmodal":"Confirmmodal",children:i?(0,s.jsxs)("div",{className:"modalConfirm-wrapper",onClick:n=>(n=>{n.stopPropagation()})(n),children:[(0,s.jsxs)("div",{className:"modal-header",children:[e,(0,s.jsx)("p",{className:"close",onClick:o,children:(0,s.jsx)("img",{src:"".concat("","/assets/btn_menu_close.png"),alt:"X"})})]}),(0,s.jsxs)("div",{className:"modalConfirm-content",children:[a,(0,s.jsx)("div",{children:(0,s.jsx)(t.Us,{onClick:r,children:"\ud655\uc778"})})]})]}):null})})}},1661:(n,e,i)=>{i.r(e),i.d(e,{default:()=>u});var t,s,o=i(7313),r=i(8467),a=i(3844),l=i(168),d=i(5112);const c=d.Z.div(t||(t=(0,l.Z)(["\n  width: 100%;\n  height: 100vh;\n  @media all and (max-width: 767px) {\n    width: 100%;\n    height: 100vh;\n  }\n"]))),h=d.Z.ul(s||(s=(0,l.Z)(["\n  width: 100%;\n  height: 100%;\n  display: flex;\n  justify-content: space-between;\n  align-items: center;\n  li {\n    :first-of-type {\n      width: 50%;\n      height: 100vh;\n      img {\n        width: 100%;\n        height: 100vh;\n      }\n    }\n    :last-of-type {\n      display: flex;\n      justify-content: flex-end;\n      align-items: center;\n      flex-direction: column;\n      gap: 30px;\n      margin-right: 12.5%;\n      .login-title {\n        padding-left: 65px;\n      }\n      form {\n        display: flex;\n        justify-content: flex-end;\n        align-items: center;\n        flex-direction: column;\n        gap: 25px;\n        div {\n          width: 100%;\n          display: flex;\n          justify-content: flex-end;\n          align-items: center;\n          gap: 20px;\n          font-size: 16px;\n          font-weight: 600;\n          input {\n            width: 440px;\n            height: 40px;\n            border: 1px solid #aaa;\n            border-radius: 4px;\n            padding: 0 5px;\n          }\n          button {\n            width: 440px;\n            height: 50px;\n            font-size: 16px;\n            font-weight: 600;\n            color: #fff;\n            background: #228fcf;\n            border: none;\n            border-radius: 6px;\n            cursor: pointer;\n          }\n        }\n      }\n    }\n  }\n  @media all and (max-width: 767px) {\n    width: 100%;\n    height: 100%;\n    padding-top: 50%;\n    display: flex;\n    flex-direction: column;\n    justify-content: space-between;\n    align-items: center;\n    li {\n      :first-of-type {\n        display: none;\n        img {\n          display: none;\n        }\n      }\n      :last-of-type {\n        display: flex;\n        justify-content: flex-end;\n        align-items: center;\n        flex-direction: column;\n        gap: 40px;\n        margin-right: 0;\n        .login-title {\n          padding-left: 0;\n          img {\n            width: 235px;\n            height: 20px;\n          }\n        }\n        form {\n          display: flex;\n          justify-content: flex-end;\n          align-items: center;\n          flex-direction: column;\n          gap: 25px;\n          div {\n            width: 100%;\n            display: flex;\n            justify-content: flex-end;\n            align-items: center;\n            gap: 20px;\n            font-size: 16px;\n            font-weight: 600;\n            label {\n              display: none;\n            }\n            input {\n              width: 250px;\n              height: 35px;\n              border: 1px solid #aaa;\n              border-radius: 4px;\n              padding: 0 5px;\n            }\n            button {\n              width: 250px;\n              height: 40px;\n              font-size: 14px;\n              font-weight: 600;\n              color: #fff;\n              background: #228fcf;\n              border: none;\n              border-radius: 6px;\n              cursor: pointer;\n            }\n          }\n        }\n      }\n    }\n  }\n"])));var p=i(78),f=i(5380),x=i(7307),g=(i(4120),i(6417));const u=()=>{const[n,e]=(0,o.useState)(""),[i,t]=(0,o.useState)(""),[s,l]=(0,p.FV)(f.q),[d,u]=(0,o.useState)(!1),[m,j]=(0,o.useState)(""),v=(0,r.s0)();return(0,o.useEffect)((()=>{u(!!m)}),[m]),(0,g.jsxs)(c,{children:[(0,g.jsxs)(h,{children:[(0,g.jsx)("li",{children:(0,g.jsx)("img",{src:"../../assets/Login.png",alt:"LoginImage"})}),(0,g.jsxs)("li",{children:[(0,g.jsx)("div",{className:"login-title",children:(0,g.jsx)("img",{src:"../../assets/LoginTitle.png",alt:"LoginTitle"})}),(0,g.jsxs)("form",{children:[(0,g.jsxs)("div",{children:[(0,g.jsx)("label",{htmlFor:"login-id",children:"\uc544\uc774\ub514"}),(0,g.jsx)("input",{type:"text",id:"login-id",placeholder:"\uc544\uc774\ub514\ub97c \uc785\ub825\ud574\uc8fc\uc138\uc694.",value:n,onChange:n=>(n=>{e(n.target.value)})(n)})]}),(0,g.jsxs)("div",{children:[(0,g.jsx)("label",{htmlFor:"login-password",children:"\ube44\ubc00\ubc88\ud638"}),(0,g.jsx)("input",{type:"password",id:"login-password",placeholder:"\ube44\ubc00\ubc88\ud638\ub97c \uc785\ub825\ud574\uc8fc\uc138\uc694.",value:i,onChange:n=>(n=>{t(n.target.value)})(n)})]}),(0,g.jsx)("div",{children:(0,g.jsx)("button",{onClick:async e=>{if(e.preventDefault(),n)if(i)try{const{role:e,accessToken:t,refreshToken:s,vo:o,accessTokenTime:r}=await(0,a.XB)(n,i,j);"ROLE_USER"===e&&t?(l({isLogin:!0,role:e,editableYn:null===o||void 0===o?void 0:o.editableYn,portfolioYn:null===o||void 0===o?void 0:o.portfolioYn,aboutMeYn:null===o||void 0===o?void 0:o.aboutMeYn}),v("/student")):"ROLE_COMPANY"===e&&t?(l({isLogin:!0,role:e}),v("/business"),setTimeout((()=>{"ROLE_COMPANY"===e&&((0,a.L2)(t,s),l({isLogin:!1,accessToken:null,role:""}),v("/"))}),r)):v("/")}catch(t){}else j("\ube44\ubc00\ubc88\ud638\ub97c \uc785\ub825\ud574 \uc8fc\uc138\uc694.");else j("\uc544\uc774\ub514\ub97c \uc785\ub825\ud574 \uc8fc\uc138\uc694.")},children:"\ub85c\uadf8\uc778"})})]})]})]}),d&&(0,g.jsx)(x.Z,{header:"",open:d,close:()=>{u(!1),j("")},onConfirm:()=>{u(!1),j("")},children:(0,g.jsx)("span",{children:m})})]})}}}]);