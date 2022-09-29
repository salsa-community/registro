/*
Copyright 2013-2020 the original author or authors from the JHipster project.
This file is part of the JHipster project, see https://jhipster.github.io/
for more information.
Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at
http://www.apache.org/licenses/LICENSE-2.0
Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
import { Component, Vue, Prop } from 'vue-property-decorator';
import ValidationsCommons from '../validations-commons/validations-commons.vue';

/**
 * An utility service for data.
 */
@Component
export default class PropertyCommons extends Vue {
  @Prop({ default: false })
  required: boolean;

  @Prop({ required: true })
  id: string;

  @Prop({ default: '' })
  label: string;

  @Prop({ default: '' })
  placeholder: string;

  @Prop({ default: false })
  readonly: boolean;

  @Prop({ default: null })
  valid: boolean;

  @Prop({ default: false })
  disabled: boolean;

  @Prop({ default: '' })
  description: string;

  @Prop({ default: '' })
  tooltip: string;

  @Prop({ default: () => ({}) })
  validationsCommons: ValidationsCommons;
}
