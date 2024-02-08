"use strict";(self.webpackChunkgreenjobgo=self.webpackChunkgreenjobgo||[]).push([[835],{3844:(n,e,a)=>{a.d(e,{L2:()=>i,XB:()=>o,ZP:()=>A});var r=a(5662),s=a(4120);const t=r.Z.create({baseURL:"/api",withXSRFToken:!0,headers:{"Content-Type":"application/json"}});t.interceptors.request.use((async n=>{const e=(0,s.ej)("accessToken");return e&&(n.headers.Authorization="Bearer ".concat(e),n.headers||(n.headers={})),n}),(n=>Promise.reject(n))),t.interceptors.response.use((n=>n),(async n=>{const{config:e,response:a}=n,r=(0,s.ej)("refreshToken");if(401===(null===a||void 0===a?void 0:a.status)&&r)try{var o;const{data:n}=await t.post("/sign/refresh-token",{refreshToken:r}),a=n;if((0,s.d8)("accessToken",a),null!==e&&void 0!==e&&e.headers&&null!==(o=e.headers)&&void 0!==o&&o.Authorization){e.headers.Authorization="Bearer ".concat(a);return await t(e)}}catch(n){console.log("\uad00\ub9ac\uc790\uc5d0\uac8c \ubb38\uc758\ud574\uc8fc\uc138\uc694.")}return Promise.reject(n)}));const o=async(n,e,a)=>{try{const r=await t.post("/sign/sign-in",{email:n,pw:e}),{role:o,refreshToken:i,accessToken:c,vo:A,accessTokenTime:d}=await r.data;if(o&&i&&c)return(0,s.d8)("refreshToken",i),(0,s.d8)("accessToken",c),a(""),{role:o,accessToken:c,refreshToken:i,vo:A,accessTokenTime:d};throw new Error("\uc798\ubabb\ub41c \uc751\ub2f5 \ud615\uc2dd")}catch(r){c(r,a)}},i=async()=>{try{await t.post("/sign/logout"),(0,s.nJ)("accessToken"),(0,s.nJ)("refreshToken"),delete t.defaults.headers.common.Authorization}catch(n){console.error("\ub85c\uadf8\uc544\uc6c3 \uc2e4\ud328")}},c=(n,e)=>{throw 432===n.response.status?e("\uc544\uc774\ub514\ub97c \ud655\uc778 \ud574 \uc8fc\uc138\uc694"):434===n.response.status?e("\ube44\ubc00\ubc88\ud638\ub97c \ud655\uc778 \ud574 \uc8fc\uc138\uc694"):435===n.response.status?e("\uad8c\ud55c\uc774 \uc5c6\uc2b5\ub2c8\ub2e4. \ub2f4\ub2f9\uc790\uc5d0\uac8c \ubb38\uc758 \ubc14\ub78d\ub2c8\ub2e4."):500===n.response.status&&e("\uc11c\ubc84 \uc624\ub958 \uc785\ub2c8\ub2e4."),new Error("\ub85c\uadf8\uc778\uc5d0 \uc2e4\ud328\ud588\uc2b5\ub2c8\ub2e4.")},A=t},7307:(n,e,a)=>{a.d(e,{Z:()=>t});a(7313);var r=a(3607),s=a(6417);const t=n=>{let{header:e,open:a,close:t,onConfirm:o,children:i}=n;return(0,s.jsx)(r.R9,{onClick:t,children:(0,s.jsx)("div",{className:a?"openConfirmModal Confimmodal":"Confirmmodal",children:a?(0,s.jsxs)("div",{className:"modalConfirm-wrapper",onClick:n=>(n=>{n.stopPropagation()})(n),children:[(0,s.jsxs)("div",{className:"modal-header",children:[e,(0,s.jsx)("p",{className:"close",onClick:t,children:(0,s.jsx)("img",{src:"".concat("","/assets/btn_menu_close.png"),alt:"X"})})]}),(0,s.jsxs)("div",{className:"modalConfirm-content",children:[i,(0,s.jsx)("div",{children:(0,s.jsx)(r.Us,{onClick:o,children:"\ud655\uc778"})})]})]}):null})})}},4835:(n,e,a)=>{a.r(e),a.d(e,{default:()=>m});var r,s=a(7313),t=a(168),o=a(5112),i=a(3607);const c=o.Z.div(r||(r=(0,t.Z)(["\n  /* padding: 40px; */\n  & > h2 {\n    color: ",";\n    font-size: 24px;\n    font-weight: 700;\n    margin-bottom: 40px;\n    margin-top: 20px;\n  }\n  .jobmanager-content {\n    width: 1400px;\n    display: flex;\n    justify-content: flex-start;\n    align-items: center;\n    flex-wrap: wrap;\n    gap: 40px;\n    .manager-profile {\n      width: 440px;\n      height: 543px;\n      border-radius: 12px;\n      border: 1px "," solid;\n      padding: 40px;\n      .manager-img {\n        width: 170px;\n        height: 226px;\n        object-fit: contain;\n        margin-bottom: 25px;\n      }\n\n      .manager-details {\n        /* \ub2f4\ub2f9\uc790 \ud55c\ub9c8\ub514 */\n        font-weight: 500;\n        .manager-word {\n          font-size: 20px;\n          color: ",";\n          margin-bottom: 14px;\n        }\n        /* \ub2f4\ub2f9\uc790 \uc774\ub984 */\n        .manager-name {\n          font-size: 16px;\n          margin-top: 14px;\n        }\n        /* \ub2f4\ub2f9\uc790 \uc5f0\ub77d\ucc98 */\n        .manager-contact {\n          margin-top: 52px;\n          li {\n            display: flex;\n            justify-content: left;\n            align-items: center;\n            margin-top: 16px;\n            span:nth-of-type(1) {\n              width: 60px;\n              font-size: 14px;\n              font-weight: 700;\n              margin-right: 10px;\n            }\n            span:nth-of-type(2) {\n              font-weight: 400;\n              font-size: 16px;\n              letter-spacing: -0.24px;\n            }\n          }\n        }\n      }\n    }\n  }\n"])),i.Iy.black,i.Iy.grayLight2,i.Iy.black);var A=a(2051),d=a(3844);var l=a(7307),p=a(6417);const m=()=>{const[n,e]=(0,s.useState)([]),[a,r]=(0,s.useState)(!1),[t,o]=(0,s.useState)(!1),[i,m]=(0,s.useState)(""),h=n=>{n.target.src=A};return(0,s.useEffect)((()=>{(async n=>{let{setmngProflieData:e,setNothing:a,setErrorApiInfo:r}=n;try{const n=await d.ZP.get("".concat("/company/employee")),r=await n.data;return e(r),a(!1),0===r.length&&a(!0),r}catch(s){r("JobManager Info: ".concat(s.message))}})({setmngProflieData:e,setNothing:r,setErrorApiInfo:m})}),[]),(0,s.useEffect)((()=>{o(!!i)}),[i]),(0,p.jsxs)(c,{children:[(0,p.jsx)("h2",{children:"\ucde8\uc5c5\ub2f4\ub2f9\uc790 \uc548\ub0b4"}),(0,p.jsx)("div",{className:"jobmanager-content",children:null===n||void 0===n?void 0:n.map((n=>(0,p.jsxs)("div",{className:"manager-profile",children:[(0,p.jsx)("img",{src:"".concat(n.profilePic),alt:"job manager",onError:h,className:"manager-img"}),(0,p.jsxs)("div",{className:"manager-details",children:[(0,p.jsx)("p",{className:"manager-word",children:n.oneWord}),(0,p.jsxs)("p",{className:"manager-name",children:[n.name," \ucde8\uc5c5\uc9c0\uc6d0\uc2e4\uc7a5"]}),(0,p.jsxs)("ul",{className:"manager-contact",children:[(0,p.jsxs)("li",{children:[(0,p.jsx)("span",{children:"\uc0c1\ub2f4\uc804\ud654"}),(0,p.jsx)("span",{children:n.counselingNumber})]}),(0,p.jsxs)("li",{children:[(0,p.jsx)("span",{children:"\ubaa8\ubc14\uc77c"}),(0,p.jsx)("span",{children:n.phoneNumber})]}),(0,p.jsxs)("li",{children:[(0,p.jsx)("span",{children:"\uc774\uba54\uc77c"}),(0,p.jsx)("span",{children:n.email})]})]})]})]},n.iemply)))}),a&&(0,p.jsx)("div",{children:"\ucde8\uc5c5\ub2f4\ub2f9\uc790\uc758 \uc815\ubcf4\uac00 \uc5c6\uc2b5\ub2c8\ub2e4."}),t&&(0,p.jsx)(l.Z,{header:"",open:t,close:()=>{o(!1),m("")},onConfirm:()=>{o(!1),m("")},children:(0,p.jsx)("span",{children:i})})]})}},2051:n=>{n.exports="data:image/png;base64,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"}}]);