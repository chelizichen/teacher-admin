<template>
  <!-- 选项 -->
  <div>
    <el-button type="primary" size="small" @click="handle_add">添加</el-button>
  </div>
  <!-- 表格 -->
  <div>
    <el-table :data="state.list" style="width: 100%">
      <el-table-column prop="researchId" label="ID" />
      <el-table-column prop="awardDate" label="授奖日期" />
      <el-table-column prop="paperDate" label="论文日期" />
      <el-table-column prop="paperIssue" label="论文问题" />
      <el-table-column prop="paperJournal" label="纸质期刊" />
      <el-table-column prop="paperPages" label="论文页数" />
      <el-table-column prop="paperTitle" label="论文标题" />
      <el-table-column prop="paperVolume" label="论文卷数" />
      <el-table-column prop="researchTopic" label="研究课题" />
      <el-table-column label="操作">
        <template #default="scope">
          <el-button type="primary" size="small" @click="handle_edit(scope.row)">编辑</el-button>
          <el-popconfirm title="确认要删除吗?" @confirm="handle_del(scope.row)">
            <template #reference>
              <el-button type="danger" size="small">删除</el-button>
            </template>
          </el-popconfirm>

        </template>
      </el-table-column>
    </el-table>
  </div>
  <Edit :dialogFormVisible="dialogFormVisible" :val="dialogVal" @close="handle_close" @success="handle_success"></Edit>
</template>

<script setup lang="ts">

import { onMounted, reactive, ref } from 'vue';

import Edit from './edit.vue'
import _ from 'lodash'
import { ElNotification } from 'element-plus';
import { Pagination, Research, Teacher } from '../../../types/dto';
import { ResearchAPI } from '../../../api';

const state = reactive({
  list: <Array<Research>>[]
})
const pagination = ref<Pagination>({
  size: 10,
  keyword: "",
  page: 1
})
// getCarPortListByCommunityId
const dialogFormVisible = ref(false)
const dialogVal = ref<any>()
function handle_close() {
  dialogFormVisible.value = false
}
function handle_success(args: any) {
  // Notification
  ElNotification({
    title: "通知",
    message: args
  })
  init()
}
function handle_edit(item: any) {
  dialogFormVisible.value = true
  const newItem = _.cloneDeep(item)
  dialogVal.value = newItem
}

function handle_add() {
  dialogFormVisible.value = true
  dialogVal.value = {}
}

async function handle_del(item: Research) {
  const data = await ResearchAPI.del({ id: item.teacherId })
  init()
}

async function init() {

  const data = await ResearchAPI.list(pagination.value)
  state.list = data.list
  console.log(data);

}

onMounted(() => {
  init()
})

</script>

<style scoped></style>