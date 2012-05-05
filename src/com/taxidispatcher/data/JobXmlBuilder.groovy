package com.taxidispatcher.data

import com.taxidispatcher.dao.JobDao
import com.taxidispatcher.dao.JobDaoImpl
import com.taxidispatcher.model.CustomerAccount
import com.taxidispatcher.model.Job
import com.taxidispatcher.model.JobAddress
import com.taxidispatcher.model.JobStop
import com.taxidispatcher.model.JobStopPK
import com.taxidispatcher.model.Taxi
import groovy.xml.MarkupBuilder

class JobXmlBuilder {

	private def builder;
	private Job job;
	def writer;

	JobXmlBuilder(job){
		this.writer = new StringWriter();
		this.builder = new MarkupBuilder(this.writer);
		this.job=job;
	}
	
	JobXmlBuilder(){
		this.writer = new StringWriter();
		this.builder = new MarkupBuilder(writer);
	}

	String	generateXml(){
		builder.Job(jobId:job.getJobId()){
			RoundTrip("N");
			/**
			 * get the pickup address from the list.
			 */
			JobDao jobDao = new JobDaoImpl();
			List<JobAddress> jobAddressList = jobDao.getJobAddress(job);

			PickUp(){
				for(JobAddress pickupAddress : jobAddressList){
					if("PICKUP_ADDRESS".equals(pickupAddress.getJobAddressType())){
						PickUpZone(pickupAddress.getZoneId());
						PickUpTime(job.getPickUpTime());
						NoOfPassenger(job.getNoOfPassenger());
						PickUpAddress(){
							Address1(pickupAddress.getStreetAddress());
							Address2(pickupAddress.getAddress2());
							City(pickupAddress.getCity());
							District(pickupAddress.getDistrict());
							Notes(pickupAddress.getJobAddressNotes());
						}
						break;
					}
				}
			}
			DropOff(){
				for(JobAddress dropOffAddress : jobAddressList){
					if("DROPOFF_ADDRESS".equals(dropOffAddress.getJobAddressType())){
						DropOffZone(dropOffAddress.getZoneId());
						DropOffTime();
						NoOfPassenger(job.getNoOfPassenger());
						DropOffAddress(){
							Address1(dropOffAddress.getStreetAddress());
							Address2(dropOffAddress.getAddress2());
							City(dropOffAddress.getCity());
							District(dropOffAddress.getDistrict());
							Notes(dropOffAddress.getJobAddressNotes());
						}
					}
				}
			}
			JobNotes(job.getDriverInstruction());
			//Customer Stops
			List<JobStop> jobStops = job.getTblJobStops();
			if(jobStops!=null && jobStops.size() > 0){
				Stops(){
					for(JobStop js : jobStops){
						Stop("StopSeq":js.getId().getJobStopSeqId()){
							StopAddress(){
								JobAddress stopAddress = js.tblJobAddress;
								Address1(stopAddress.getStreetAddress());
								Address2(stopAddress.getAddress2());
								City(stopAddress.getCity());
								District(stopAddress.getDistrict());
								Notes(stopAddress.getJobAddressNotes());
							}
						}
					}
				}
			}
			
			//customer detail
			CustomerAccount custAcc = job.getTblCustomerAccount();
			Customer(){
				Name(custAcc.getCustomerName());
				AccountId(custAcc.getCustomerAccountId());
				Phone1(custAcc.getCustomerPhone1())
				Phone2(custAcc.getCustomerPhone2());
			}
			
			//taxi detail info
			
			Taxi taxi = job.getTblTaxi();
			Taxi(){
				TaxiCode(taxi.getTaxiId());
				TaxiPlateNumber(taxi.getPlateNumber());
				DeviceCode(taxi.getDeviceId());
			}
			

		}
		return writer.toString();
	}
	
	/**
	 * XML generation for testing purposes with hardcoded data.
	 * @return
	 */
	String generateTestXml(){		
		builder.Job(jobId:"1234"){
			RoundTrip("N");
			
			PickUp(){
				
						PickUpZone("104");
						PickUpTime("10:30");
						NoOfPassenger("2");
						PickUpAddress(){
							Address1("37-52,j 62nd st");
							Address2("patli galli");
							City("Naya Baneshwor");
							District("Bagmati");
							Notes("near bajeko sekuwa");
						
				}
			}
			DropOff(){
				
						DropOffZone("106");
						DropOffTime("10:30");
						NoOfPassenger("2");
						DropOffAddress(){
							Address1("37-52,j 62nd st");
							Address2("patli galli");
							City("Naya Baneshwor");
							District("Bagmati");
							Notes("near bajeko sekuwa");
						
				}
			}
			JobNotes("Bistari Chala sale");
			
		}
		return writer.toString();
	}



}
