"use strict";(self.webpackChunkgreenjobgo=self.webpackChunkgreenjobgo||[]).push([[563],{8212:(n,e,t)=>{t.d(e,{Dc:()=>l,WI:()=>o,XM:()=>s,_I:()=>r,fW:()=>d,oN:()=>a,vn:()=>c});var i=t(3965);const s=async(n,e,t,s,o,a,l)=>{try{const l="/student/file?istudent=".concat(n,"&iFileCategory=").concat(e);let d;switch(e){case 2:d="".concat(l,"&oneWord=").concat(s);break;case 3:d="".concat(l,"&oneWord=").concat(o,"&fileLink=").concat(t)}const r=await i.Lp.post(d,a,{headers:{"Content-Type":"multipart/form-data"}});return 200===r.status?{success:!0}:{success:!1}}catch(d){const{status:n}=d.response;if(d.response)switch(n){case 452:case 453:case 454:case 456:case 457:case 458:l("".concat(d.response.data.message));break;default:l("\ub4f1\ub85d\uc5d0 \uc2e4\ud328\ud588\uc2b5\ub2c8\ub2e4.")}else l("\ub124\ud2b8\uc6cc\ud06c \uc624\ub958 \ub610\ub294 \uc11c\ubc84 \uc751\ub2f5\uc774 \uc5c6\uc2b5\ub2c8\ub2e4.")}},o=async(n,e,t)=>{try{const t=await i.Lp.post("/student/file?istudent=".concat(n,"&iFileCategory=4"),e,{headers:{"Content-Type":"multipart/form-data"}});return 200===t.status?{success:!0}:{success:!1}}catch(s){const{status:n}=s.response;if(s.response)switch(n){case 452:case 453:case 454:case 456:case 457:case 458:t("".concat(s.response.data.message));break;default:t("\ub4f1\ub85d\uc5d0 \uc2e4\ud328\ud588\uc2b5\ub2c8\ub2e4.")}else t("\ub124\ud2b8\uc6cc\ud06c \uc624\ub958 \ub610\ub294 \uc11c\ubc84 \uc751\ub2f5\uc774 \uc5c6\uc2b5\ub2c8\ub2e4.")}},a=async(n,e,t,s)=>{try{const s=await i.Lp.post("/student/file?istudent=".concat(e,"&iFileCategory=1&introducedLine=").concat(t),n,{headers:{"Content-Type":"multipart/form-data"}});console.log("\uc774\ub825\uc11c \uc804\uc1a1 \uc131\uacf5",s.data),console.log("\uc774\ub825\uc11c \uc804\uc1a1 \uc131\uacf5",s.status);return 200===s.status?{success:!0}:{success:!1}}catch(o){const{status:n}=o.response;if(o.response)switch(n){case 452:case 453:case 454:case 456:case 457:case 458:s("".concat(o.response.data.message));break;default:s("\ub4f1\ub85d\uc5d0 \uc2e4\ud328\ud588\uc2b5\ub2c8\ub2e4.")}else s("\ub124\ud2b8\uc6cc\ud06c \uc624\ub958 \ub610\ub294 \uc11c\ubc84 \uc751\ub2f5\uc774 \uc5c6\uc2b5\ub2c8\ub2e4.")}},l=async(n,e)=>{try{const t=await i.Lp.post("/student/certificate?istudent=".concat(n,"&certificates=").concat(e));return 200===t.status?{success:!0}:{success:!1}}catch(t){console.log(t)}},d=async(n,e,t,s)=>{try{const t=await i.Lp.patch("/student/portfolio-main?istudent=".concat(n,"&ifile=").concat(e));console.log("main",t)}catch(o){console.log("main",o);const{status:n}=o.response;if(o.response)switch(n){case 452:case 453:case 454:case 456:case 457:case 458:s("".concat(o.response.data.message));break;default:s("Main Portfolio Select: ".concat(o.message))}else s("Main Portfolio Select: ".concat(o.message))}},r=async(n,e,t)=>{try{await i.Lp.delete("/student/file?istudent=".concat(n,"&ifile=").concat(e));t("\uc0ad\uc81c\uac00 \uc644\ub8cc\ub418\uc5c8\uc2b5\ub2c8\ub2e4.")}catch(s){t("file delete: ".concat(s.message))}},c=async(n,e)=>{try{const t=await i.Lp.delete("/student/certificate?istudent=".concat(n,"&icertificate=").concat(e));return 200===t.status?{success:!0}:{success:!1}}catch(t){console.log(t)}}},3965:(n,e,t)=>{t.d(e,{L2:()=>l,Lp:()=>o,XB:()=>a});var i=t(5294),s=t(2707);const o=i.Z.create({baseURL:"/api",headers:{"Content-Type":"application/json"}});o.interceptors.request.use((async n=>{const e=(0,s.ej)("accessToken");return e&&(n.headers.Authorization="Bearer ".concat(e)),n}),(n=>(console.log(n),Promise.reject(n)))),o.interceptors.response.use((n=>n),(async n=>{const{config:e,response:t}=n,i=(0,s.ej)("refreshToken");if(401===t.status&&i)try{const{data:n}=await o.post("/sign/refresh-token",{refreshToken:i}),t=n;if((0,s.d8)("accessToken",t),e.headers&&e.headers.Authorization){e.headers.Authorization="Bearer ".concat(t);return await o(e)}}catch(n){console.log("\ud1a0\ud070 \uac31\uc2e0 \uc2e4\ud328:",n)}return console.error("\uc694\uccad \uc2e4\ud328:",n),Promise.reject(n)}));const a=async(n,e,t)=>{try{const i=await o.post("/sign/sign-in",{email:n,pw:e}),{role:a,refreshToken:l,accessToken:d,vo:r,accessTokenTime:c}=await i.data;if(console.log(i.data),!(a&&l&&d))throw new Error("\uc798\ubabb\ub41c \uc751\ub2f5 \ud615\uc2dd");if((0,s.d8)("refreshToken",l),(0,s.d8)("accessToken",d),t(""),"ROLE_USER"===a)return{role:a,accessToken:d,refreshToken:l,vo:r};if("ROLE_COMPANY"===a)return{role:a,accessToken:d,refreshToken:l,vo:r,accessTokenTime:c}}catch(i){throw 432===i.response.status&&t("\uc544\uc774\ub514\ub97c \ud655\uc778 \ud574 \uc8fc\uc138\uc694"),434===i.response.status&&t("\ube44\ubc00\ubc88\ud638\ub97c \ud655\uc778 \ud574 \uc8fc\uc138\uc694"),435===i.response.status&&t("\uad8c\ud55c\uc774 \uc5c6\uc2b5\ub2c8\ub2e4. \ub2f4\ub2f9\uc790\uc5d0\uac8c \ubb38\uc758 \ubc14\ub78d\ub2c8\ub2e4."),500===i.response.status&&t("\uc11c\ubc84 \uc624\ub958 \uc785\ub2c8\ub2e4."),new Error("\ub85c\uadf8\uc778\uc5d0 \uc2e4\ud328\ud588\uc2b5\ub2c8\ub2e4.")}},l=async(n,e)=>{try{await o.post("/sign/logout");(0,s.nJ)(n),(0,s.nJ)(e)}catch(t){console.log(t)}}},6381:(n,e,t)=>{t.d(e,{A:()=>s});var i=t(3965);const s=async n=>{try{const n=await i.Lp.get("/student"),{std:e,file:t}=n.data;return console.log(n.data),console.log(n.data),{std:e,file:t}}catch(e){await n("Student Info: ".concat(e.message))}}},5002:(n,e,t)=>{t.d(e,{py:()=>p,pf:()=>x,V1:()=>h});var i,s,o=t(168),a=t(7686),l=t(2291);const d=a.Z.div(i||(i=(0,o.Z)(["\n  position: absolute;\n  width: 100%;\n  height: 100%;\n  top: 0;\n  left: 0;\n  .dim {\n    position: fixed;\n    top: 0;\n    right: 0;\n    bottom: 0;\n    left: 0;\n    z-index: 1000;\n    background-color: rgba(0, 0, 0, 0.3);\n    backdrop-filter: blur(2px);\n  }\n  .content-wrap {\n    position: absolute;\n    width: 21.5%;\n    height: 15.5vh;\n    top: 50%;\n    left: 50%;\n    padding: 15px 20px;\n    text-align: center;\n    border-radius: 10px;\n    display: flex;\n    flex-flow: column;\n    justify-content: space-between;\n    background-color: ",";\n    transform: translate(-50%, -50%);\n    animation: modal-show 0.3s;\n    z-index: 1000;\n    > div {\n      text-align: center;\n    }\n    h2 {\n      display: flex;\n      font-size: 18px;\n      width: 100%;\n    }\n    .header {\n      display: flex;\n      justify-content: flex-end;\n      font-size: 12px;\n      cursor: pointer;\n    }\n    .content {\n      display: flex;\n      font-size: 14px;\n      font-weight: 400;\n    }\n    .btns {\n      display: flex;\n      justify-content: flex-end;\n      align-items: center;\n      gap: 5px;\n      button {\n        width: 65px;\n        height: 32px;\n        line-height: 32px;\n        background: #176b87;\n        color: #fff;\n        border: 1px solid;\n        border-radius: 4px;\n        cursor: pointer;\n        :first-of-type {\n          background: #7e7e7e;\n        }\n        :last-of-type {\n          background: #228fcf;\n        }\n      }\n    }\n  }\n"])),l.Iy.white),r=a.Z.div(s||(s=(0,o.Z)(["\n  position: absolute;\n  width: 100%;\n  height: 100%;\n  top: 0;\n  left: 0;\n  .dim {\n    position: fixed;\n    top: 0;\n    right: 0;\n    bottom: 0;\n    left: 0;\n    z-index: 1000;\n    background-color: rgba(0, 0, 0, 0.3);\n    backdrop-filter: blur(2px);\n  }\n  .content-wrap {\n    position: absolute;\n    width: 21.5%;\n    height: 15.5vh;\n    top: 50%;\n    left: 50%;\n    padding: 15px 20px;\n    text-align: center;\n    border-radius: 10px;\n    display: flex;\n    flex-flow: column;\n    justify-content: space-between;\n    background-color: ",";\n    transform: translate(-50%, -50%);\n    animation: modal-show 0.3s;\n    z-index: 1000;\n    > div {\n      text-align: center;\n    }\n    .header {\n      display: flex;\n      justify-content: flex-end;\n      font-size: 12px;\n      cursor: pointer;\n    }\n    .content {\n      display: flex;\n      font-size: 14px;\n      font-weight: 400;\n    }\n    .btns {\n      display: flex;\n      justify-content: flex-end;\n      align-items: center;\n      gap: 5px;\n      button {\n        width: 65px;\n        height: 32px;\n        line-height: 32px;\n        background: #176b87;\n        color: #fff;\n        border: 1px solid;\n        border-radius: 4px;\n        cursor: pointer;\n        :first-of-type {\n          background: #7e7e7e;\n        }\n        :last-of-type {\n          background: #228fcf;\n        }\n      }\n    }\n  }\n"])),l.Iy.white);var c=t(184);const p=n=>{let{acceptOkModal:e,uploadResult:t,handleOk:i}=n;return(0,c.jsx)(c.Fragment,{children:e&&(0,c.jsx)(d,{children:(0,c.jsx)("div",{className:"dim",children:(0,c.jsxs)("div",{className:"content-wrap",children:[(0,c.jsx)("div",{className:"header",children:(0,c.jsx)("span",{onClick:i,children:"\u2716"})}),(0,c.jsx)("div",{className:"content",children:(0,c.jsx)("span",{children:t?"\ub4f1\ub85d\uc774 \uc644\ub8cc\ub418\uc5c8\uc2b5\ub2c8\ub2e4.":"\ub4f1\ub85d \uc2e4\ud328 : \ub2e4\uc2dc \uc2dc\ub3c4\ud574\uc8fc\uc138\uc694."})}),(0,c.jsx)("div",{className:"btns",children:(0,c.jsx)("button",{onClick:i,children:"\ud655\uc778"})})]})})})})},h=n=>{let{mainYnModal:e,handleMainPofolOk:t,handleMainCancel:i,mainYn:s}=n;return(0,c.jsx)(c.Fragment,{children:e&&(0,c.jsx)(d,{children:(0,c.jsx)("div",{className:"dim",children:(0,c.jsxs)("div",{className:"content-wrap",children:[(0,c.jsx)("div",{className:"header",children:(0,c.jsx)("span",{children:"\u2716"})}),0===s?(0,c.jsx)("div",{className:"content",children:(0,c.jsx)("span",{children:"\ub300\ud45c \ud3ec\ud2b8\ud3f4\ub9ac\uc624\ub85c \ub4f1\ub85d\ud558\uc2dc\uaca0\uc2b5\ub2c8\uae4c?"})}):(0,c.jsx)("div",{className:"content",children:(0,c.jsx)("span",{children:"\ub300\ud45c \ud3ec\ud2b8\ud3f4\ub9ac\uc624 \ub4f1\ub85d\uc744 \ucde8\uc18c\ud558\uc2dc\uaca0\uc2b5\ub2c8\uae4c?"})}),(0,c.jsxs)("div",{className:"btns",children:[(0,c.jsx)("button",{onClick:i,children:"\ucde8\uc18c"}),(0,c.jsx)("button",{onClick:t,children:"\ud655\uc778"})]})]})})})})},x=n=>{let{deleteOkModal:e,handleDeleteOk:t,handleDeleteCancel:i}=n;return(0,c.jsx)(c.Fragment,{children:e&&(0,c.jsx)(r,{children:(0,c.jsx)("div",{className:"dim",children:(0,c.jsxs)("div",{className:"content-wrap",children:[(0,c.jsx)("div",{className:"header",children:(0,c.jsx)("span",{onClick:i,children:"\u2716"})}),(0,c.jsx)("div",{className:"content",children:(0,c.jsx)("span",{children:"\ud574\ub2f9 \ud56d\ubaa9\uc744 \uc0ad\uc81c \ud558\uc2dc\uaca0\uc2b5\ub2c8\uae4c?"})}),(0,c.jsxs)("div",{className:"btns",children:[(0,c.jsx)("button",{onClick:i,children:"\ucde8\uc18c"}),(0,c.jsx)("button",{onClick:t,children:"\ud655\uc778"})]})]})})})})}},3508:(n,e,t)=>{t.d(e,{Z:()=>o});t(2791);var i=t(2291),s=t(184);const o=n=>{let{header:e,open:t,close:o,onConfirm:a,children:l}=n;console.log("\uc624\ucf00\uc774 \ubaa8\ub2ec \uc791\ub3d9\ub429\ub2c8\ub2e4");return(0,s.jsx)(i.R9,{onClick:o,children:(0,s.jsx)("div",{className:t?"openConfirmModal Confimmodal":"Confirmmodal",children:t?(0,s.jsxs)("div",{className:"modalConfirm-wrapper",onClick:n=>(n=>{n.stopPropagation()})(n),children:[(0,s.jsxs)("div",{className:"modal-header",children:[e,(0,s.jsx)("p",{className:"close",onClick:o,children:(0,s.jsx)("img",{src:"".concat("","/assets/btn_menu_close.png"),alt:"X"})})]}),(0,s.jsxs)("div",{className:"modalConfirm-content",children:[l,(0,s.jsx)("div",{children:(0,s.jsx)(i.Us,{onClick:a,children:"\ud655\uc778"})})]})]}):null})})}},3563:(n,e,t)=>{t.r(e),t.d(e,{default:()=>N});var i,s,o=t(2791),a=t(8212),l=t(168),d=t(7686),r=t(2291);const c=d.Z.div(i||(i=(0,l.Z)(["\n  padding-top: 30px;\n  width: 1400px;\n  .addpofol-title {\n    padding: 0 0 40px 0;\n    h2 {\n      font-size: 24px;\n      font-weight: 700;\n      color: ",";\n    }\n  }\n  .addpofol-content {\n    border: 1px solid ",";\n    border-radius: 12px;\n  }\n  .addpofol-buttons {\n    display: flex;\n    justify-content: center;\n    align-items: center;\n    gap: 20px;\n    padding: 40px 0 20px 0;\n    > button {\n      width: 150px;\n      height: 50px;\n      color: ",";\n      border: none;\n      border-radius: 6px;\n      cursor: pointer;\n      background: ",";\n    }\n  }\n"])),r.Iy.black,r.Iy.maingray,r.Iy.white,r.Iy.btn),p=d.Z.div(s||(s=(0,l.Z)(["\n  display: flex;\n  flex-direction: column;\n  width: 100%;\n  max-height: 750px;\n  .addpofol-header {\n    width: 100%;\n    height: 70px;\n    border-radius: 12px 12px 0 0;\n    background: ",";\n    padding: 0 40px;\n    > h2 {\n      font-size: 20px;\n      font-weight: 500;\n      line-height: 70px;\n      color: ",";\n    }\n  }\n  .addpofol-inner {\n    overflow-y: auto;\n    max-height: 100%;\n    > div {\n      padding: 0 40px;\n      :first-of-type {\n        padding-top: 40px;\n        > span {\n          font-size: 18px;\n          font-weight: 500;\n          color: ",";\n        }\n        .file-box {\n          display: flex;\n          align-items: center;\n          gap: 10px;\n          padding-top: 14px;\n          .upload-name {\n            display: inline-block;\n            width: 570px;\n            height: 40px;\n            line-height: 40px;\n            padding: 0 10px;\n            border: 1px solid ",";\n            border-radius: 4px;\n            color: ",";\n          }\n          label {\n            display: inline-block;\n            width: 110px;\n            height: 40px;\n            line-height: 40px;\n            color: ",";\n            border-radius: 6px;\n            text-align: center;\n            background-color: ",';\n            cursor: pointer;\n          }\n          input[type="file"] {\n            position: absolute;\n            width: 0;\n            height: 0;\n            padding: 0;\n            overflow: hidden;\n            border: 0;\n          }\n          > div {\n            display: flex;\n            padding-left: 10px;\n            gap: 5px;\n            button {\n              width: 85px;\n              height: 40px;\n              border: none;\n              border-radius: 6px;\n              cursor: pointer;\n              :first-of-type {\n                color: ',";\n                background: ",";\n              }\n              :last-of-type {\n                color: ",";\n                background: ",";\n                border: 1px solid ",";\n              }\n            }\n          }\n        }\n        > p {\n          color: ",";\n          padding: 8px 0 0 4px;\n        }\n      }\n      :last-of-type {\n        max-height: 100%;\n        padding-top: 46px;\n        div {\n          :nth-of-type(1) {\n            display: flex;\n            align-items: center;\n            gap: 18px;\n            font-size: 18px;\n            font-weight: 500;\n            color: ",";\n            > p {\n              font-size: 14px;\n              font-weight: 400;\n              color: ",";\n            }\n          }\n          :nth-of-type(2) {\n            padding: 14px 0;\n            > button {\n              width: 690px;\n              height: 40px;\n              color: ",";\n              background: ",";\n              border: none;\n              border-radius: 6px;\n              cursor: pointer;\n            }\n          }\n          :nth-of-type(3) {\n            display: flex;\n            flex-direction: column;\n            gap: 14px;\n            padding-bottom: 40px;\n            > ul {\n              display: flex;\n              flex-direction: column;\n              gap: 5px;\n              li {\n                :first-of-type {\n                  display: flex;\n                  justify-content: flex-start;\n                  align-items: center;\n                  > div {\n                    :nth-of-type(1) {\n                      display: flex;\n                      justify-content: space-between;\n                      width: 690px;\n                      height: 40px;\n                      border-radius: 6px;\n                      background: ",";\n                      > div {\n                        display: flex;\n                        align-items: center;\n                        gap: 10px;\n                        padding: 0 13px 0 13px;\n                        a {\n                          font-size: 14px;\n                          font-weight: 400;\n                          color: ",";\n                        }\n                      }\n                      .main-pofol {\n                        font-size: 14px;\n                        background: ",";\n                        border-radius: 4px;\n                        > span {\n                          width: 70px;\n                          height: 28px;\n                          display: flex;\n                          justify-content: center;\n                          align-items: center;\n                          gap: 5px;\n                          color: ",";\n                        }\n                      }\n                      .delete-file {\n                        display: flex;\n                        justify-content: center;\n                        align-items: center;\n                        img {\n                          cursor: pointer;\n                        }\n                      }\n                    }\n                    :nth-of-type(2) {\n                      display: flex;\n                      justify-content: center;\n                      align-items: center;\n                      gap: 8px;\n                      padding-left: 16px;\n                    }\n                  }\n                }\n                :last-of-type {\n                  width: 690px;\n                  height: 60px;\n                  line-height: 18px;\n                  padding: 10px;\n                  border: 1px solid ",";\n                  border-radius: 6px;\n                }\n              }\n            }\n          }\n        }\n      }\n    }\n  }\n"])),r.Iy.sectiontitle,r.Iy.white,r.Iy.black,r.Iy.input,r.Iy.input,r.Iy.white,r.Iy.grayDeep,r.Iy.white,r.Iy.sectiontitle,r.Iy.sectiontitle,r.Iy.white,r.Iy.sectiontitle,r.Iy.red,r.Iy.black,r.Iy.red,r.Iy.white,r.Iy.grayDeep,r.Iy.search,r.Iy.black,r.Iy.admintxt,r.Iy.white,r.Iy.input);var h=t(6030),x=(t(3497),t(9806)),f=t(1632),u=t(4193),g=t(184);const m=n=>{var e,t,i,s,o,a;let{file:l,handleAddModalOpen:d,imgFile:r,handleImgFileChange:c,handleThumbNailUpload:m,handleDeleteFile:b,handleCheckboxChange:j}=n;const y=(0,h.sJ)(u.w),v=null===(e=y.std)||void 0===e?void 0:e.istudent;return(0,g.jsxs)(p,{children:[(0,g.jsx)("div",{className:"addpofol-header",children:(0,g.jsx)("h2",{children:"\ud3ec\ud2b8\ud3f4\ub9ac\uc624 \ucca8\ubd80"})}),(0,g.jsxs)("div",{className:"addpofol-inner",children:[(0,g.jsxs)("div",{children:[(0,g.jsx)("span",{children:"\ud3ec\ud2b8\ud3f4\ub9ac\uc624 \ub300\ud45c \uc774\ubbf8\uc9c0"}),(0,g.jsxs)("div",{className:"file-box",children:[(0,g.jsx)("input",{type:"file",id:"imgfile",accept:".jpg, png, jpeg, gif",onChange:c}),(0,g.jsx)("label",{htmlFor:"imgfile",children:"\ud30c\uc77c\ucca8\ubd80"}),(0,g.jsx)("input",{className:"upload-name",value:null!==l&&void 0!==l&&null!==(t=l.img)&&void 0!==t&&t.ifile?null===l||void 0===l||null===(i=l.img)||void 0===i?void 0:i.img:r?r.name:"\ucca8\ubd80\ud30c\uc77c",readOnly:!0}),(0,g.jsxs)("div",{children:[(0,g.jsx)("button",{onClick:m,children:"\ub4f1\ub85d"}),(0,g.jsx)("button",{onClick:()=>{var n,e;b(null===(n=y.file)||void 0===n||null===(e=n.img)||void 0===e?void 0:e.ifile)},children:"\uc0ad\uc81c"})]})]}),(0,g.jsx)("p",{children:"*\ubcf8 \uc774\ub825\uc11c\uc758 \uc378\ub124\uc77c\ub85c \uc0ac\uc6a9\ud560 \uc774\ubbf8\uc9c0\ub97c \ucca8\ubd80\ud574 \uc8fc\uc138\uc694."})]}),(0,g.jsxs)("div",{children:[(0,g.jsxs)("div",{children:[(0,g.jsx)("span",{children:"\ud3ec\ud2b8\ud3f4\ub9ac\uc624"}),(0,g.jsx)("p",{children:"*\ub300\ud45c \uc774\ubbf8\uc9c0\uc5d0 \ud574\ub2f9\ud558\ub294 \ud3ec\ud2b8\ud3f4\ub9ac\uc624\ub294 \uc81c\uc77c \ucc98\uc74c\uc5d0 \ucca8\ubd80\ud574\uc8fc\uc138\uc694."})]}),(0,g.jsx)("div",{children:(0,g.jsx)("button",{onClick:d,children:"+ \ud3ec\ud2b8\ud3f4\ub9ac\uc624 \ud30c\uc77c \ub610\ub294 \ub9c1\ud06c \ucd94\uac00"})}),(0,g.jsxs)("div",{children:[(null===l||void 0===l||null===(s=l.portfolio)||void 0===s?void 0:s.length)>0&&(null===(o=l.portfolio)||void 0===o?void 0:o.map((n=>(0,g.jsxs)("ul",{children:[(0,g.jsxs)("li",{children:[(0,g.jsxs)("div",{children:[(0,g.jsxs)("div",{children:[(0,g.jsx)("img",{src:"".concat("","/assets/ph_file.png"),alt:"portfolio"}),(0,g.jsx)("a",{href:"https://greenjobgo.kr/img/student/".concat(v,"/").concat(n.file),target:"_blank",rel:"noopener noreferrer",children:n.file})]}),(0,g.jsxs)("div",{children:[1===(null===n||void 0===n?void 0:n.mainYn)?(0,g.jsx)("div",{className:"main-pofol",children:(0,g.jsxs)("span",{children:[(0,g.jsx)(x.G,{icon:f.nmd,style:{color:"#fff"}}),"\ub300\ud45c"]})}):"",(0,g.jsx)("div",{className:"delete-file",onClick:()=>{b(n.ifile)},children:(0,g.jsx)("img",{src:"".concat("","/assets/ph_x-bold.png"),alt:"portfolio"})})]})]}),(0,g.jsxs)("div",{children:[(0,g.jsx)("input",{type:"checkbox",value:n.ifile,checked:1===n.mainYn,onChange:e=>j(e,n.ifile)}),(0,g.jsx)("label",{htmlFor:"",children:"\ub300\ud45c \ud3ec\ud2b8\ud3f4\ub9ac\uc624\ub85c \uc124\uc815"})]})]}),(0,g.jsx)("li",{children:(0,g.jsx)("span",{children:n.oneWord})})]},n.ifile)))),(null===l||void 0===l||null===(a=l.fileLinks)||void 0===a?void 0:a.length)>0&&l.fileLinks.map((n=>(0,g.jsxs)("ul",{children:[(0,g.jsxs)("li",{children:[(0,g.jsxs)("div",{children:[(0,g.jsxs)("div",{children:[(0,g.jsx)("img",{src:"".concat("","/assets/ph_link.png"),alt:"portfolio"}),(0,g.jsx)("a",{href:"https://greenjobgo.kr/img/student/".concat(v,"/").concat(n.fileLink),target:"_blank",rel:"noopener noreferrer",children:n.fileLink})]}),(0,g.jsxs)("div",{children:[1===(null===n||void 0===n?void 0:n.mainYn)?(0,g.jsx)("div",{className:"main-pofol",children:(0,g.jsxs)("span",{children:[(0,g.jsx)(x.G,{icon:f.nmd,style:{display:"block",color:"#fff"}}),"\ub300\ud45c"]})}):"",(0,g.jsx)("div",{className:"delete-file",onClick:()=>{b(n.ifile)},children:(0,g.jsx)("img",{src:"".concat("","/assets/ph_x-bold.png"),alt:"portfolio"})})]})]}),(0,g.jsxs)("div",{children:[(0,g.jsx)("input",{type:"checkbox",value:n.ifile,checked:1===n.mainYn,onChange:e=>j(e,n.ifile)}),(0,g.jsx)("label",{htmlFor:"",children:"\ub300\ud45c \ud3ec\ud2b8\ud3f4\ub9ac\uc624\ub85c \uc124\uc815"})]})]}),(0,g.jsx)("li",{children:(0,g.jsx)("span",{children:n.oneWord})})]},n.ifile)))]})]})]})]})};var b;const j=d.Z.div(b||(b=(0,l.Z)(["\n  position: absolute;\n  width: 100%;\n  height: 100%;\n  top: 0;\n  left: 0;\n\n  .dim {\n    position: fixed;\n    top: 0;\n    right: 0;\n    bottom: 0;\n    left: 0;\n    z-index: 1000;\n    background-color: rgba(0, 0, 0, 0.3);\n    backdrop-filter: blur(2px);\n  }\n  .add-modal-inner {\n    position: absolute;\n    width: 782px;\n    height: 494px;\n    top: 50%;\n    left: 50%;\n    text-align: center;\n    overflow: hidden;\n    border-radius: 10px;\n    background-color: ",';\n    transform: translate(-50%, -50%);\n    animation: modal-show 0.3s;\n    z-index: 1000;\n    padding-bottom: 40px;\n  }\n  .add-modal-top {\n    display: flex;\n    justify-content: center;\n    align-items: center;\n    height: 70px;\n    box-shadow: 0px 2px 6px 0px rgba(0, 0, 0, 0.15);\n    > div {\n      :last-of-type {\n        position: absolute;\n        top: 3%.5;\n        right: 3.5%;\n        font-size: 21px;\n        cursor: pointer;\n      }\n    }\n  }\n  .add-modal-btm {\n    padding: 0px 40px;\n    li {\n      :nth-of-type(3) {\n        padding-top: 14px;\n        > textarea {\n          width: 702px;\n          height: 100px;\n          resize: none;\n          padding: 10px;\n          border: 1px solid #aaa;\n          border-radius: 4px;\n        }\n      }\n      :nth-of-type(4) {\n        text-align: start;\n        padding-top: 14px;\n        > span {\n          display: block;\n          font-size: 14px;\n          padding-bottom: 8px;\n          color: #eb5757;\n        }\n      }\n      :last-of-type {\n        padding-top: 32px;\n        > button {\n          width: 150px;\n          height: 50px;\n          font-size: 16px;\n          color: #fff;\n          background: #6d6d6d;\n          border: 1px solid #6d6d6d;\n          border-radius: 6px;\n          cursor: pointer;\n        }\n      }\n    }\n    .radio-box {\n      display: flex;\n      align-items: center;\n      gap: 6px;\n      font-size: 16px;\n      padding: 40px 0 14px 0;\n      > label {\n        :first-of-type {\n          margin-right: 18px;\n        }\n      }\n      > input {\n        width: 16px;\n        height: 16px;\n        line-height: 16px;\n      }\n    }\n    .file-box {\n      display: flex;\n      align-items: center;\n      width: 100%;\n      gap: 10px;\n      .upload-link {\n        width: 702px;\n        height: 40px;\n        padding: 0 10px;\n        border: 1px solid #a4a4a4;\n        border-radius: 6px;\n      }\n      .upload-name {\n        display: inline-block;\n        width: 584px;\n        height: 40px;\n        line-height: 40px;\n        padding: 0 10px;\n        border: 1px solid #a4a4a4;\n        border-radius: 6px;\n        color: #6d6d6d;\n      }\n      label {\n        display: inline-block;\n        height: 40px;\n        color: #fff;\n        border-radius: 6px;\n        padding: 10px 30px;\n        vertical-align: middle;\n        background-color: #6d6d6d;\n        cursor: pointer;\n      }\n      input[type="file"] {\n        position: absolute;\n        width: 0;\n        height: 0;\n        padding: 0;\n        overflow: hidden;\n        border: 0;\n      }\n    }\n  }\n'])),r.Iy.white),y=n=>{let{modalOpen:e,handleAddModalClose:t,fileType:i,setFileType:s,selectFile:o,setSelectFile:a,linkUrl:l,setLinkUrl:d,fileOneWord:r,setFileOneWord:c,linkOneWord:p,setLinkOneWord:h,handleFileUpload:x}=n;const f=n=>{const e=n.target.value.slice(0,150);2===i?c(e):3===i&&h(e)};return(0,g.jsx)(j,{children:e&&(0,g.jsx)("div",{className:"dim",children:(0,g.jsxs)("div",{className:"add-modal-inner",children:[(0,g.jsxs)("div",{className:"add-modal-top",children:[(0,g.jsx)("div",{children:(0,g.jsx)("h2",{children:"\ud3ec\ud2b8\ud3f4\ub9ac\uc624 \ucd94\uac00"})}),(0,g.jsx)("div",{children:(0,g.jsx)("span",{onClick:t,children:"\u2716"})})]}),(0,g.jsxs)("ul",{className:"add-modal-btm",children:[(0,g.jsxs)("li",{className:"radio-box",children:[(0,g.jsx)("input",{type:"radio",id:"fileTypeFile",name:"fileType",value:2,checked:2===i,onChange:()=>{s(2)}}),(0,g.jsx)("label",{htmlFor:"fileTypeFile",children:"\ud30c\uc77c\ucd94\uac00"}),(0,g.jsx)("input",{type:"radio",id:"fileTypeLink",name:"fileType",value:3,checked:3===i,onChange:()=>s(3)}),(0,g.jsx)("label",{htmlFor:"fileTypeLink",children:"\ub9c1\ud06c\ucd94\uac00"})]}),2===i?(0,g.jsxs)(g.Fragment,{children:[(0,g.jsxs)("li",{className:"file-box",children:[(0,g.jsx)("input",{type:"file",id:"file",accept:".pdf",onChange:n=>{const e=n.target.files[0];e&&a(e)}}),(0,g.jsx)("label",{htmlFor:"file",children:"\ud30c\uc77c\ucca8\ubd80"}),(0,g.jsx)("input",{className:"upload-name",value:o?o.name:"\ucca8\ubd80\ud30c\uc77c",placeholder:"PDF \ucca8\ubd80\ud30c\uc77c",readOnly:!0})]}),(0,g.jsx)("li",{children:(0,g.jsx)("textarea",{cols:"30",rows:"5",placeholder:"\ud3ec\ud2b8\ud3f4\ub9ac\uc624 \uc18c\uac1c \ub0b4\uc6a9\uc744 \uc791\uc131\ud574\uc8fc\uc138\uc694.(\ucd5c\ub300 150\uc790)",value:r,onChange:f})}),(0,g.jsxs)("li",{children:[(0,g.jsx)("span",{children:"*\ud3ec\ud2b8\ud3f4\ub9ac\uc624 \ud30c\uc77c\uc740 \ucd5c\ub300 5\uac1c\uae4c\uc9c0 \ub4f1\ub85d \uac00\ub2a5\ud558\uba70, \ud55c\ubc88\uc5d0 1\uac1c\uc758 \ud30c\uc77c\ub9cc \ub4f1\ub85d \uac00\ub2a5\ud569\ub2c8\ub2e4."}),(0,g.jsx)("span",{children:"*\ud30c\uc77c \uc6a9\ub7c9\uc740 \ucd5c\ub300 50MB\uae4c\uc9c0\ub9cc \ucca8\ubd80 \uac00\ub2a5\ud569\ub2c8\ub2e4."})]})]}):3===i?(0,g.jsxs)(g.Fragment,{children:[(0,g.jsx)("li",{className:"file-box",children:(0,g.jsx)("input",{className:"upload-link",placeholder:"\ud3ec\ud2b8\ud3f4\ub9ac\uc624 \ub9c1\ud06c\uc8fc\uc18c\ub97c \uc785\ub825\ud574\uc8fc\uc138\uc694.",value:l,onChange:n=>d(n.target.value)})}),(0,g.jsx)("li",{children:(0,g.jsx)("textarea",{cols:"30",rows:"5",placeholder:"\ud3ec\ud2b8\ud3f4\ub9ac\uc624 \uc18c\uac1c \ub0b4\uc6a9\uc744 \uc791\uc131\ud574\uc8fc\uc138\uc694.(\ucd5c\ub300 150\uc790)",value:p,onChange:f})}),(0,g.jsx)("li",{children:(0,g.jsx)("span",{children:"*\ud3ec\ud2b8\ud3f4\ub9ac\uc624 \ub9c1\ud06c\ub294 \ucd5c\ub300 5\uac1c\uae4c\uc9c0 \ub4f1\ub85d \uac00\ub2a5\ud558\uba70, \ud55c\ubc88\uc5d0 1\uac1c\uc758 \ub9c1\ud06c\ub9cc \ub4f1\ub85d \uac00\ub2a5\ud569\ub2c8\ub2e4."})})]}):null,(0,g.jsx)("li",{children:(0,g.jsx)("button",{onClick:x,children:"\ub4f1\ub85d"})})]})]})})})};var v=t(5002),k=t(6381),w=t(7689),C=t(3508);const N=()=>{var n;const[e,t]=(0,o.useState)(!1),[i,s]=(0,o.useState)(""),[l,d]=(0,o.useState)(2),[r,p]=(0,o.useState)(""),[x,f]=(0,o.useState)(""),[b,j]=(0,o.useState)(""),[N,I]=(0,o.useState)(""),[F,T]=(0,o.useState)(""),[L,O]=(0,o.useState)(""),[z,S]=(0,o.useState)(0),[M,W]=(0,o.useState)([]),[A,Z]=(0,o.useState)([]),[Y,_]=(0,o.useState)(!1),[D,U]=(0,o.useState)(!1),[P,E]=(0,o.useState)(!1),[R,J]=(0,o.useState)(!1),[B,X]=(0,o.useState)(""),[G,V]=(0,o.useState)(""),q=null===(n=(0,h.sJ)(u.w).std)||void 0===n?void 0:n.istudent,H=(0,w.s0)(),K=async()=>{const{std:n,file:e}=await(0,k.A)(s);W(e),Z(n)};(0,o.useEffect)((()=>{K()}),[]);return(0,o.useEffect)((()=>{t(!!i)}),[i]),(0,g.jsxs)(c,{children:[Y&&(0,g.jsx)(y,{modalOpen:Y,handleAddModalClose:()=>{_(!1)},fileType:l,setFileType:d,selectFile:r,setSelectFile:p,linkUrl:b,setLinkUrl:j,fileOneWord:N,setFileOneWord:I,linkOneWord:F,setLinkOneWord:T,handleFileUpload:async()=>{let n=new FormData;n.append("file",r);try{const e=await(0,a.XM)(q,l,b,N,F,n,s);O(e),!0===e.success&&(_(!1),U(!0))}catch(e){U(!0)}}}),D&&(0,g.jsx)(v.py,{acceptOkModal:D,uploadResult:L,handleOk:()=>{U(!1),K()}}),P&&(0,g.jsx)(v.V1,{mainYnModal:P,handleMainPofolOk:async()=>{await(0,a.fW)(q,B,z,s),E(!1),K()},handleMainCancel:()=>{E(!1)},mainYn:z}),R&&(0,g.jsx)(v.pf,{deleteOkModal:R,handleDeleteOk:async()=>{const n=A.istudent;await(0,a._I)(n,G,s),J(!1),K()},handleDeleteCancel:()=>{J(!1)}}),(0,g.jsx)("div",{className:"addpofol-title",children:(0,g.jsx)("h2",{children:"\ud3ec\ud2b8\ud3f4\ub9ac\uc624 \ub4f1\ub85d"})}),(0,g.jsx)("div",{className:"addpofol-content",children:(0,g.jsx)(m,{file:M,handleAddModalOpen:()=>{_(!0)},imgFile:x,handleImgFileChange:n=>{const e=n.target.files[0];e&&f(e)},handleThumbNailUpload:async()=>{let n=new FormData;n.append("file",x);try{const e=await(0,a.WI)(q,n,s);O(e),!0===e.success&&(_(!1),U(!0))}catch(e){}},handleDeleteFile:n=>{n?(V(n),J(!0),K()):s("\uc0ad\uc81c\ud560 \ud30c\uc77c\uc774 \uc5c6\uc2b5\ub2c8\ub2e4.")},handleCheckboxChange:(n,e)=>{n.target.checked?(X([e]),S(0),E(!0),console.log(B)):(X([e]),S(1),E(!0),console.log(B)),K()}})}),(0,g.jsx)("div",{className:"addpofol-buttons",children:(0,g.jsx)("button",{onClick:()=>{H("/student/myportfolio")},children:"\uba54\uc778\uc73c\ub85c \ub3cc\uc544\uac00\uae30"})}),e&&(0,g.jsx)(C.Z,{header:"",open:e,close:()=>{t(!1),s("")},onConfirm:()=>{t(!1),s("")},children:(0,g.jsx)("span",{children:i})})]})}},4193:(n,e,t)=>{t.d(e,{w:()=>a});var i=t(6030),s=t(9386);const{persistAtom:o}=(0,s.J)(),a=(0,i.cn)({key:"userInfoAtom",default:{std:{istudent:0,age:0,gender:"",name:"",birthday:"",address:"",email:"",startedAt:"",endedAt:"",mobileNumber:"",education:"",introducedLine:"",huntJobYn:0,subject:{icourseSubject:0,subjectName:""},certificates:[{icertificate:0,certificate:""}]},file:{img:{ifile:0,img:""},resume:{ifile:0,resume:"",oneWord:""},portfolio:[{ifile:0,file:"",oneWord:"",mainYn:0}],fileLinks:[{ifile:0,fileLink:"",oneWord:""}]},portfolioYn:0,aboutMeYn:0},effects_UNSTABLE:[o]})},3497:(n,e,t)=>{var i=t(6030),s=t(3965),o=t(4193);(0,i.nZ)({key:"userInfo",get:async()=>{try{const n=await(0,s.Lp)("/student"),{std:e,file:t}=n.data;return{std:e,file:t}}catch(n){console.log(n)}},set:n=>{let{set:e}=n;e(o.w)}})}}]);
//# sourceMappingURL=563.0f7529a6.chunk.js.map