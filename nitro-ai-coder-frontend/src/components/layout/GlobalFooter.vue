<template>
  <a-layout-footer class="global-footer">
    <div class="footer-content">
      <p class="copyright">yuanbaoer</p>
    </div>
  </a-layout-footer>
</template>

<script setup lang="ts">
// 暂无逻辑
</script>

<style scoped>
.global-footer {
  background: #f0f2f5;
  padding: 16px 0;
  text-align: center;
  border-top: 1px solid #e8e8e8;
  position: sticky;
  bottom: 0;
  z-index: 1000;
}

.footer-content {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 24px;
}

.copyright {
  margin: 0;
  color: #666;
  font-size: 14px;
}

/* 响应式设计 */
@media (max-width: 768px) {
  .footer-content {
    padding: 0 16px;
  }
  
  .copyright {
    font-size: 12px;
  }
}
</style>
